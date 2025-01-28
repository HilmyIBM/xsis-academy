package com.xsis.bc345.be.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/category")
public class CategoryController {

    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCategory() {
        var data = categoryService.getAll();

        if (data.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Page<?> getAllCategoryPaging(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return categoryService.getAllPaging(page,size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return new ResponseEntity<>(categoryService.getById(id, false), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoryModel category) {
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CategoryModel categoryModel) {
        return new ResponseEntity<>(categoryService.updateCategory(categoryModel), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody CategoryModel model) {
        return new ResponseEntity<>(categoryService.deleteCategory(model), HttpStatus.OK);
    }

//    @GetMapping("/delete/{id}")
//    public ResponseEntity<?> getDeletedById(@PathVariable int id) {
//        var data = categoryService.getById(id, true);
//
//        if (data.isEmpty())
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with id %s doesn't exists".formatted(id));
//
//        System.out.println(data.get());
//
//        return new ResponseEntity<>(data.get(), HttpStatus.OK);
//    }

//    @GetMapping("/name/{name}")
//    public ResponseEntity<?> getByName(@PathVariable String name) {
//        return request.process(
//                categoryService.getByName(name),
//                HttpStatus.OK, HttpStatus.NO_CONTENT);
//    }
//
//    @GetMapping("/description/{desc}")
//    public ResponseEntity<?> getByDescription(@PathVariable String desc) {
//        return request.process(
//                categoryService.getByDesc(desc),
//                HttpStatus.OK, HttpStatus.NO_CONTENT);
//    }
//
//    @GetMapping("/filter/{filter}")
//    public ResponseEntity<?> getByNameOrDesc(@PathVariable String filter) {
//        return request.process(
//                categoryService.getByNameOrDescription(filter),
//                HttpStatus.OK, HttpStatus.NO_CONTENT);
//    }
}
