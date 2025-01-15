package com.xsis.bc345.be.controllers;

import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("*")
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    private CategoryService categorySvc;
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try {
            List<Category> data = categorySvc.getAll();
            return new ResponseEntity<List<Category>>(
                data, data.size() > 0 ?  HttpStatus.OK :  HttpStatus.NO_CONTENT
            );
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getBy(@PathVariable Integer id) {
        try {
            Optional<Category> data = categorySvc.getById(id);

            return new ResponseEntity<Category>(
                data.get(), data.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT
            );
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/name/{categoryName}")
    public ResponseEntity<?> getBy(@PathVariable String categoryName) {
        try {
            Optional<List<Category>> data = categorySvc.getByCategoryName(categoryName);
            return new ResponseEntity<List<Category>>(
                data.get(), data.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT
            );
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<?> getByFilter(@PathVariable String filter) {
        try {
            Optional<List<Category>> data = categorySvc.getByNameOrDescription(filter);
            return new ResponseEntity<List<Category>>(
                data.get(), data.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT
            );
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody final Category data) {
        try {
            return new ResponseEntity<Category>(categorySvc.create(data), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody final Category data) {
        try {
            return new ResponseEntity<Category>(categorySvc.update(data), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestBody final Category data) {
        try {
            return new ResponseEntity<Category>(categorySvc.delete(data), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
}
