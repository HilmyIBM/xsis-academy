package com.xsis.bc345.be.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        List<CategoryModel> modelList = categoryService.getAll();

        if (modelList.isEmpty())
            return new ResponseEntity<>("Data Is Empty!", HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(modelList, HttpStatus.OK);
    }

}
