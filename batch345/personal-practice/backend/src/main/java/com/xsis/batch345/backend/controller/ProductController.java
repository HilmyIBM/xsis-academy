package com.xsis.batch345.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.batch345.backend.model.Product;
import com.xsis.batch345.backend.model.Variant;
import com.xsis.batch345.backend.service.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/product")
public class ProductController {
  
  @Autowired
  private ProductService productService;
  
  @GetMapping("")
  public ResponseEntity<?> getAll() {
    try {
      Optional<List<Product>> data = productService.findAll();
      return new ResponseEntity<List<Product>>(
        data.get(),
        data.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT
      );
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<?> getById(@PathVariable int id) {
    try {
      Optional<Product> data = productService.findById(id);

      return new ResponseEntity<Product>(
        data.get(),
        data.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT
      );
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
