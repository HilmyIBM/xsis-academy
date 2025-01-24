package com.xsis.bc345.be.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.be.models.Product;
import com.xsis.bc345.be.services.ProductService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin("*")
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductService productSvc;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            List<Map<String, Object>> data = productSvc.getAllNative();
            if (data.size() == 0) {
                return new ResponseEntity<List<Product>>(new ArrayList<Product>(), HttpStatus.OK);
            }
            return new ResponseEntity<List<Map<String, Object>>>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addProduct(@RequestBody Product data) {
        try {
            return new ResponseEntity<Product>(productSvc.create(data), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            Map<String, Object> data = productSvc.getById(id);
            if (data == null || data.isEmpty()) {
                return new ResponseEntity<String>("No product found with ID: " + id, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("An error occurred while processing your request.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody final Product data) {
        try {
            Product newProduct = productSvc.update(data);
            return new ResponseEntity<Product>(newProduct, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("delete/{id}/{userId}")
    public ResponseEntity<?> delete(@PathVariable int id, @PathVariable int userId) {
        try {
            Product data = productSvc.delete(id, userId);
            if (data.isDeleted()) {
                return new ResponseEntity<Product>(data, HttpStatus.OK);
            }
            return new ResponseEntity<String>("Failed to delete variant", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
