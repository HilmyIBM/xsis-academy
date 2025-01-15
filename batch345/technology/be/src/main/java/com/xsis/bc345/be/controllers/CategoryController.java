package com.xsis.bc345.be.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.be.models.Category;
import com.xsis.bc345.be.services.CategoryService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@CrossOrigin("*")
@RequestMapping("api/category")
public class CategoryController {
    // Langsung diinject tanpa perlu constructor
    @Autowired
    private CategoryService categorySvc;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            // Returning the array of data
            List<Category> data = categorySvc.getAll();

            if (data.size() == 0) {
                return new ResponseEntity<List<Category>>(new ArrayList<Category>(), HttpStatus.OK); // returning array
                                                                                                     // [] // []
            }
            return new ResponseEntity<List<Category>>(data, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            Optional<Category> data = categorySvc.getById(id);
            System.out.println(data);
            if (data.isEmpty()) {
                return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<Category>(data.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("filter/{filter}")
    public ResponseEntity<?> getByCategoryNameOrDescription(@PathVariable String filter) {
        try {
            List<Category> data = categorySvc.getByNameOrDescription(filter);
            return new ResponseEntity<List<Category>>(data, data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("name/{name}")
    public ResponseEntity<?> getName(@PathVariable String name) {
        try {
            List<Category> data = categorySvc.getByName(name);
            return new ResponseEntity<List<Category>>(data, data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody final Category data) {
        try {
            Category newCategory = categorySvc.create(data);
            return new ResponseEntity<Category>(newCategory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody final Category data) {
        try {
            Category newCategory = categorySvc.update(data);
            return new ResponseEntity<Category>(newCategory, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody final Category data) {
        //TODO: process PUT request
        try {
            Category newCategory = categorySvc.update(data);
            return new ResponseEntity<>(newCategory,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
}
