package com.xsis.bc345.backend.controllers;

import java.util.List;
import java.util.Map;
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

import com.xsis.bc345.backend.models.ProductModel;
import com.xsis.bc345.backend.models.VariantModel;
import com.xsis.bc345.backend.services.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productSVC;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
         try {
            final List<Map<String,Object>> data= productSVC.getNative();
            if (data.size()>0) {
                return new ResponseEntity<List<Map<String,Object>>> (data,HttpStatus.OK);   
            }else{
                return new ResponseEntity<List<Map<String,Object>>>(data,HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }  

    @PostMapping("")
    public ResponseEntity<?> addProduct(@RequestBody final ProductModel data) {
        try {
            return new ResponseEntity<ProductModel>(productSVC.create(data),HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> updateproduct(@RequestBody final ProductModel data){
        try {
            return new ResponseEntity<ProductModel>(productSVC.update(data),HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("id/{id}")
    public ResponseEntity<?> getId(@PathVariable int id){
        try {
            final List<Map<String,Object>> data=productSVC.getNativeId(id);
            if (data.size()>0) {
                return new ResponseEntity<List<Map<String,Object>>>(data,HttpStatus.OK);   
            }else{
                return new ResponseEntity<List<Map<String,Object>>>(data,HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
    }

    @DeleteMapping("delete/{id}/{userId}")
    public ResponseEntity<?> delete(@PathVariable int id,@PathVariable int userId){
        try {
            ProductModel data=productSVC.delete(id,userId);
            if (data.isIsDeleted()) {
                return new ResponseEntity<ProductModel>(data,HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Gagal Hapus",HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("filter/{filter}")
    public ResponseEntity<?> getbyFilter(@PathVariable String filter){
         try {
            final List<Map<String,Object>> data= productSVC.getfilterNative(filter);
            if (data.size()>0) {
                return new ResponseEntity<List<Map<String,Object>>> (data,HttpStatus.OK);   
            }else{
                return new ResponseEntity<List<Map<String,Object>>>(data,HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }  
    
}
