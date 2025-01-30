package com.xsis.be.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.be.models.Category;
import com.xsis.be.services.CategoryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




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
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("paginate/{page}/{size}")
    public ResponseEntity<?> getAll(@PathVariable int page, @PathVariable int size, @RequestParam(defaultValue = "id") String sort, @RequestParam(defaultValue = "ASC") String sd){
        try {
            Page<Category> data = categorySvc.getAll(PageRequest.of(page, size, Sort.by(Direction.fromString(sd), sort)));
            return new ResponseEntity<Page<Category>>(
                data, HttpStatus.OK
            );
        } catch (Exception e) {
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
            Optional<List<Map<String,Object>>> data = categorySvc.getByNameOrDescription(filter);
            return new ResponseEntity<List<Map<String,Object>>>(
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
    @DeleteMapping("delete/{id}/{userId}")
    public ResponseEntity<?> delete(@PathVariable int id, @PathVariable int userId) {
        try {
            Category data = categorySvc.delete(id, userId);
            if(data.isDeleted()){
                return new ResponseEntity<Category>(data, HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("failed to delete category", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
