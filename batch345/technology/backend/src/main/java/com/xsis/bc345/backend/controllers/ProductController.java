package com.xsis.bc345.backend.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.backend.models.Product;
import com.xsis.bc345.backend.models.Variant;
import com.xsis.bc345.backend.services.ProductService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



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


  @GetMapping("/native/paginated/{page}/{size}")
  public ResponseEntity<?> getAllNative(@PathVariable int page, @PathVariable int size) { 
    try {
      Page<Map<String, Object>> data = productSvc.getAllNative(PageRequest.of(page, size));
      return new ResponseEntity<Page<Map<String, Object>>>(
        data, HttpStatus.OK
      );
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/native/paginatedfilter/{filter}/{page}/{size}")
  public ResponseEntity<?> getByFilterNative(@PathVariable String filter, @PathVariable int page, @PathVariable int size) {
    try {
      Page<Map<String, Object>> data = productSvc.getByFilterNative(PageRequest.of(page, size),filter);
      return new ResponseEntity<Page<Map<String, Object>>>(
        data, HttpStatus.OK
      );
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<?> getBy(@PathVariable int id) {
    try {
      Optional<Product> data = productSvc.getById(id);

      return new ResponseEntity<Product>(
        data.get(),
        data.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT
      );
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("native/id/{id}")
  public ResponseEntity<?> getByIdNative(@PathVariable int id) {
    try {
      Optional<Map<String, Object>> data = productSvc.getByIdNative(id);

      return new ResponseEntity<Map<String, Object>>(
        data.get(),
        data.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT
      );
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PostMapping("")
  public ResponseEntity<?> create(@RequestBody final Product data) {
      try {
        return new ResponseEntity<Product>(productSvc.create(data), HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @PutMapping("")
  public ResponseEntity<?> update(@RequestBody final Product data) {
      try {
        return new ResponseEntity<Product>(productSvc.update(data), HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
  } 

  @DeleteMapping("/delete/{id}/{userId}")
  public ResponseEntity<?> delete(@PathVariable int id, @PathVariable int userId) {
    try {
      Product data = productSvc.delete(id, userId);
      if (data.isDeleted()) {
        return new ResponseEntity<Product>(data, HttpStatus.OK);
      }else{
        return new ResponseEntity<String>("Failed to delete product", HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  

}
