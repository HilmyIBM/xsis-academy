package com.xsis.bc345.be.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.be.models.Product;
import com.xsis.bc345.be.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin("*")
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductService productSvc;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            List<Product> data = productSvc.getAll();
            if (data.size() == 0){
                return new ResponseEntity<List<Product>>(new ArrayList<Product>(),HttpStatus.OK);
            }

            return new ResponseEntity<List<Product>>(data,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
