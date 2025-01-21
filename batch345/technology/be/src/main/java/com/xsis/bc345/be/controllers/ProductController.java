package com.xsis.bc345.be.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.be.models.Category;
import com.xsis.bc345.be.models.Product;
import com.xsis.bc345.be.services.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private ProductService productSvc;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try {
            List<Map<String, Object>> data = productSvc.getAll();
            return new ResponseEntity<List<Map<String, Object>>>(data, data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // CREATE NEW DATA
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody final Product data) {
        try {
            return new ResponseEntity<Product>(productSvc.create(data), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
