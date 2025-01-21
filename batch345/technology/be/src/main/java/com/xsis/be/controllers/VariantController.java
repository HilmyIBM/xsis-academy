package com.xsis.be.controllers;

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

import com.xsis.be.models.Category;
import com.xsis.be.models.Variant;
import com.xsis.be.services.VariantService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/variant")
public class VariantController {
    @Autowired
    private VariantService variantSvc;
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            // List<Variant> data = variantSvc.getAll();
            List<Map<String, Object>> data = variantSvc.getAllNative();
            // return new ResponseEntity<List<Variant>>(data, data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
            return new ResponseEntity<List<Map<String, Object>>>(data, data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody final Variant data) {
        try {
            return new ResponseEntity<Variant>(variantSvc.create(data), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody final Variant data) {
        try {
            return new ResponseEntity<Variant>(variantSvc.update(data), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getBy(@PathVariable Integer id) {
        try {
            Optional<Variant> data = variantSvc.getById(id);

            return new ResponseEntity<Variant>(
                data.get(), data.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT
            );
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/filter/{filter}")
    public ResponseEntity<?> getByFilter(@PathVariable String filter) {
        try {
            List<Map<String,Object>> data = variantSvc.getByNameOrDescription(filter);
            return new ResponseEntity<List<Map<String,Object>>>(
                data, data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT
            );
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("delete/{id}/{userId}")
    public ResponseEntity<?> delete(@PathVariable int id, @PathVariable int userId) {
        try {
            Variant data = variantSvc.delete(id, userId);
            if(data.isDeleted()){
                return new ResponseEntity<Variant>(data, HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("failed to delete category", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
