package com.xsis.bc345.backend.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.backend.models.CategoryModel;
import com.xsis.bc345.backend.services.CategoryService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin("*")
@RequestMapping("api/category")
public class CategoryController{
    @Autowired
    private CategoryService categorySVC;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try {
            List<CategoryModel> data= categorySVC.getALL();
            if (data.size()>0) {
                return new ResponseEntity<List<CategoryModel>>(data,HttpStatus.OK);   
            }else{
                return new ResponseEntity<List<CategoryModel>>(data,HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getbyId(@PathVariable int id){
        try {
            Optional<CategoryModel> data=categorySVC.getbyId(id);

            if (data.isPresent()) {
                return new ResponseEntity<CategoryModel>(data.get(),HttpStatus.OK);   
            }else{
                return new ResponseEntity<CategoryModel>(data.get(),HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
    }

    @GetMapping("/name/{categoryName}")
    public ResponseEntity<?> getbyName(@PathVariable String categoryName){
        try {
            List<CategoryModel> data= categorySVC.getbyname(categoryName);
            return new ResponseEntity<List<CategoryModel>>(data,data.size()>0 ? HttpStatus.OK: HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<?> getbyfilter(@PathVariable String filter){
        try {
            List<CategoryModel> data= categorySVC.getbynameOrdescription(filter);
            return new ResponseEntity<List<CategoryModel>>(data,data.size()>0 ? HttpStatus.OK: HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody final CategoryModel data){
        try {
            return new ResponseEntity<CategoryModel>(categorySVC.create(data),HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("")
    public ResponseEntity<?> edit(@RequestBody final CategoryModel data){
        try {
            return new ResponseEntity<CategoryModel>(categorySVC.update(data),HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


