package com.xsis.bc345.be.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.be.models.Product;
import com.xsis.bc345.be.services.ProductService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productSvc;
    
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            List<Map<String, Object>> data = productSvc.getAll().get();
            
            return new ResponseEntity<List<Map<String, Object>>>(
                data,
                data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT
            );
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
    
    @GetMapping("/paginated/{page}/{size}")
    public ResponseEntity<?> getAll(@PathVariable int page, @PathVariable int size, @RequestParam(defaultValue = "id") String sort, @RequestParam(defaultValue = "ASC") String sd) {
        try {
            Page<Map<String, Object>> data = productSvc.getAll(PageRequest.of(page, size, Sort.by(Direction.fromString(sd), sort)));
            
            return new ResponseEntity<Page<Map<String, Object>>>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getBy(@PathVariable final int id) {
        try {
            Optional<Map<String, Object>> data = productSvc.getBy(id);
            
            return new ResponseEntity<Map<String, Object>>(
                data.get(),
                data.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT
            );
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
    
    @GetMapping("/filter/{filter}")
    public ResponseEntity<?> getBy(@PathVariable final String filter) {
        try {
            List<Map<String, Object>> data = productSvc.getBy(filter);
            
            return new ResponseEntity<List<Map<String, Object>>>(
                data,
                data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT
            );
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     
    @GetMapping("/paginatedfilter/{filter}/{page}/{size}")
    public ResponseEntity<?> getBy(@PathVariable final String filter, @PathVariable int page, @PathVariable int size) {
        try {
            Page<Map<String, Object>> data = productSvc.getBy(filter, PageRequest.of(page, size));
            
            return new ResponseEntity<Page<Map<String, Object>>>(
                data,
                data.getNumberOfElements() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT
            );
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Product data) {
        try {
            return new ResponseEntity<Product>(productSvc.create(data), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Product data) {
        try {
            return new ResponseEntity<Product>(productSvc.create(data), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}/{userId}")
    public ResponseEntity<?> delete(
        @PathVariable int id,
        @PathVariable int userId
    ) {
        try {
            final Product data = productSvc.delete(id, userId);

            if (data.isDeleted()){
                return new ResponseEntity<Product> (data, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<String>("Failed to delete Variant with ID = (" + id + ")", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
