package com.xsis.bc345.be.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.be.models.Variant;
import com.xsis.bc345.be.services.VariantService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("api/variant")
public class VariantController {
    @Autowired
    private VariantService variantSvc;
 
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            final List<Variant> data = variantSvc.getAll();

            return new ResponseEntity<List<Variant>>(
                data,
                data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT
            );
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getBy(@PathVariable final int id) {
        try {
            final Optional<Variant> data = variantSvc.getBy(id);
            
            return new ResponseEntity<Variant>(
                data.get(),
                data.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT
            );
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/filter/{filter}")
    public ResponseEntity<?> getBy(@PathVariable final String filter) {
        try {
            final Optional<List<Variant>> data = variantSvc.getBy(filter);
            
            return new ResponseEntity<List<Variant>>(
                data.get(),
                data.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT
            );
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody final Variant data) {
        try {
            return new ResponseEntity<Variant>(variantSvc.create(data), HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody final Variant data) {
        try {
            return new ResponseEntity<Variant>(variantSvc.create(data), HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
