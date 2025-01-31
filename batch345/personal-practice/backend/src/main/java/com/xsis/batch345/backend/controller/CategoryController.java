package com.xsis.batch345.backend.controller;

import java.util.List;
import java.util.Optional;

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

import com.xsis.batch345.backend.model.Category;
import com.xsis.batch345.backend.service.CategoryService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;


  @GetMapping("")
  public ResponseEntity<?> getAll() {
    try {

      List<Category> data = categoryService.findAll().get();
      return new ResponseEntity<List<Category>>(
        data,
        data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT
      );
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<?> getById(@PathVariable int id) {
    try {
      Optional<Category> data = categoryService.findById(id);

      return new ResponseEntity<Category>(
        data.get(),
        data.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT
      );
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/filter/{filter}")
  public ResponseEntity<?> getByFilter(@PathVariable String filter) {
    try {
      Optional<List<Category>> data = categoryService.findByFilter(filter);

      return new ResponseEntity<List<Category>>(
        data.get(),
        data.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT
      );
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("")
  public ResponseEntity<?> create(@RequestBody final Category data) {
      try {
        return new ResponseEntity<Category>(categoryService.create(data), HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @PutMapping("")
  public ResponseEntity<?> update(@RequestBody final Category data) {
      try {
        return new ResponseEntity<Category>(categoryService.update(data), HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @DeleteMapping("/delete/{id}/{userId}")
  public ResponseEntity<?> delete(@PathVariable int id, @PathVariable int userId) {
    try {
      Category data = categoryService.delete(id, userId);
      if (data.isDeleted()) {
        return new ResponseEntity<Category>(data, HttpStatus.OK);
      }else{
        return new ResponseEntity<String>("Failed to delete category", HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
