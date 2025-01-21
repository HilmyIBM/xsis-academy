package com.xsis.backend.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.backend.models.Product;
import com.xsis.backend.services.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            final List<Map<String, Object>> data = productService.getAll();
            return new ResponseEntity<List<Map<String, Object>>>(data,
                    (data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<?> getByFilter(@PathVariable String filter) {
        try {
            List<Map<String, Object>> data = productService.getListByFilter(filter);
            return new ResponseEntity<List<Map<String, Object>>>(data,
                    (data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody final Product data) {
        try {
            return new ResponseEntity<Product>(productService.create(data), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            Map<String, Object> data = productService.getById(id);
            return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody final Product data) {
        try {
            return new ResponseEntity<Product>(productService.update(data), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}/{userId}")
    public ResponseEntity<?> delete(@PathVariable int id, @PathVariable int userId) {
        try {
            Product data = productService.delete(id, userId);
            if (data.isDeleted()) {
                return new ResponseEntity<Product>(data, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Failed to delete variant!", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
