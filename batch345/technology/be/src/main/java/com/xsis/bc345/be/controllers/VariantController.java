package com.xsis.bc345.be.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.be.models.Variant;
import com.xsis.bc345.be.services.VariantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin("*")
@RequestMapping("api/variant")
public class VariantController {

    @Autowired
    private VariantService variantSvc;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            List<Map<String, Object>> data = variantSvc.getAllNative();
            if (data.isEmpty()) {
                return new ResponseEntity<List<Variant>>(new ArrayList<Variant>(), HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Map<String, Object>>>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            Optional<Map<String, Object>> data = variantSvc.getByIdNative(id);
            if (data.isEmpty()) {
                return new ResponseEntity<Variant>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<Map<String, Object>>(data.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody final Variant data) {
        try {
            Variant newVariant = variantSvc.create(data);
            return new ResponseEntity<Variant>(newVariant, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody final Variant data) {
        try {
            Variant existingVariant = variantSvc.update(data);
            return new ResponseEntity<Variant>(existingVariant, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("delete/{id}/{userId}")
    public ResponseEntity<?> delete(@PathVariable int id, @PathVariable int userId) {
        try {
            Variant data = variantSvc.delete(id, userId);
            if (data.isDeleted()) {
                return new ResponseEntity<Variant>(data, HttpStatus.OK);
            }
            return new ResponseEntity<String>("Failed to delete variant", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
