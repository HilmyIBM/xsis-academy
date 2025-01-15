package com.xsis.bc345.be.category;

import com.xsis.bc345.be.util.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final Request request;

    @Autowired
    public CategoryController(CategoryService categoryService, Request request) {
        this.categoryService = categoryService;
        this.request = request;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return request.process(
                categoryService.getAll(),
                HttpStatus.OK, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return request.process(
                categoryService.getById(id),
                HttpStatus.OK, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        return request.process(
                categoryService.getByName(name),
                HttpStatus.OK, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/description/{desc}")
    public ResponseEntity<?> getByDescription(@PathVariable String desc) {
        return request.process(
                categoryService.getByDesc(desc),
                HttpStatus.OK, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<?> getByNameOrDesc(@PathVariable String filter) {
        return request.process(
                categoryService.getByNameOrDescription(filter),
                HttpStatus.OK, HttpStatus.NO_CONTENT);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CategoryModel category) {
        return request.process(
                categoryService.createCategory(category),
                HttpStatus.CREATED, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody CategoryModel categoryModel) {
        return request.process(
                categoryService.updateCategory(categoryModel),
                HttpStatus.OK, HttpStatus.BAD_REQUEST);
    }
}
