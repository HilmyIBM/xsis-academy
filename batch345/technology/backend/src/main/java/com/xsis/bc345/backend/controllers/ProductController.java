package com.xsis.bc345.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.backend.models.Product;
import com.xsis.bc345.backend.services.ProductService;

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
        return new ResponseEntity<List<Product>>(
          data,
          data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT
        );
      } catch (Exception e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  

}
