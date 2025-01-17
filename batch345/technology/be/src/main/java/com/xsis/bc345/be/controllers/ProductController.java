package com.xsis.bc345.be.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xsis.bc345.be.models.Product;
import com.xsis.bc345.be.services.ProductService;

@Controller
@RequestMapping("/api/product")
public class ProductController {
     @Autowired
    private ProductService productSvc;
    
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            List<Product> data = productSvc.getAll();
            
            return new ResponseEntity<List<Product>>(
                data,
                data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT
            );
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }    
}
