package com.xsis.backend.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.xsis.backend.models.Category;
import com.xsis.backend.services.CategoryService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            List<Category> data = categoryService.getAll();

            return new ResponseEntity<List<Category>>(data,
                    (data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT));

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/paginated/{page}/{size}")
    public ResponseEntity<?> getAll(@PathVariable int page, @PathVariable int size,
            @RequestParam(defaultValue = "category_name") String sort, @RequestParam(defaultValue = "ASC") String sd) {
        try {
            final Page<Map<String, Object>> data = categoryService
                    .getAll(PageRequest.of(page, size, Sort.by(Direction.fromString(sd), sort)));
            return new ResponseEntity<Page<Map<String, Object>>>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            Optional<Category> data = categoryService.getById(id);

            return new ResponseEntity<Category>(data.get(), (data.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT));

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/name/{categoryName}")
    public ResponseEntity<?> getBy(@PathVariable String categoryName) {
        try {
            List<Category> data = categoryService.getByName(categoryName);

            return new ResponseEntity<List<Category>>(
                    data, (data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<?> getByFilter(@PathVariable String filter) {
        try {
            List<Category> data = categoryService.getByFilter(filter);
            return new ResponseEntity<List<Category>>(
                    data, (data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/paginated/filter/{filter}/{page}/{size}")
    public ResponseEntity<?> getByFilter(@PathVariable String filter, @PathVariable int page, @PathVariable int size) {
        try {
            Page<Category> data = categoryService.getByFilter(filter, PageRequest.of(page, size));
            return new ResponseEntity<Page<Category>>(data, HttpStatus.OK);
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
            return new ResponseEntity<Category>(categoryService.update(data), HttpStatus.OK);
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
            } else {
                return new ResponseEntity<String>("Failed to delete category!", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
