package com.xsis.bc345.be.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.be.models.Category;
import com.xsis.bc345.be.services.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin("*")
@RequestMapping("api/category")
public class CategoryContoller {
    @Autowired
    private CategoryService categorySvc;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try {
            List<Category> data = categorySvc.getAll();
            return new ResponseEntity<List<Category>>(data, data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            Optional<Category> data = categorySvc.getById(id);
            return new ResponseEntity<Category>(data.get(),data.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/name/{categoryName}")
    public ResponseEntity<?> getById(@PathVariable String categoryName) {
        try {
            List<Category> data = categorySvc.getByName(categoryName);
            return new ResponseEntity<List<Category>>(data, data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<?> getByFilter(@PathVariable String filter) {
        try {
            List<Category> data = categorySvc.getByNameorDescription(filter);
            return new ResponseEntity<List<Category>>(data, data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // CREATE NEW DATA
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody final Category data) {
        try {
            return new ResponseEntity<Category>(categorySvc.create(data), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // CREATE NEW DATA
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody final Category data) {
        try {
            return new ResponseEntity<Category>(categorySvc.update(data), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("delete/{id}/{userId}")
    public ResponseEntity<?> delete(@PathVariable int id, @PathVariable int userId) {
        try {
            Category data = categorySvc.delete(id, userId);

            if (data.isDeleted()) {
                return new ResponseEntity<Category>(data, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Failed to delete category!", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        
}
