package com.xsis.bc345.be.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.be.models.Category;
import com.xsis.bc345.be.models.Product;
import com.xsis.bc345.be.models.Variant;
import com.xsis.bc345.be.models.Product;
import com.xsis.bc345.be.services.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private ProductService productSvc;

    // @GetMapping("")
    // public ResponseEntity<?> getAll(){
    //     try {
    //         List<Product> data = productSvc.getAll();

    //         if (data.size()>0){
    //             return new ResponseEntity<List<Product>>(data, HttpStatus.OK);
                
    //         } else {
    //             return new ResponseEntity<List<Product>>(data, HttpStatus.NO_CONTENT);
                
    //         }
    //     } catch (Exception e) {
    //         // TODO: handle exception
    //         return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    @GetMapping("")
    public ResponseEntity<?> getAllNative(){
        try {
            final List<Map<String, Object>> data = productSvc.getAllNative();

            if (data.size()>0){
                return new ResponseEntity<List<Map<String, Object>>>(data, HttpStatus.OK);
                
            } else {
                return new ResponseEntity<List<Map<String, Object>>>(data, HttpStatus.NO_CONTENT);
                
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<?> getByFilterNative(@PathVariable String filter) {
        try {
           List<Map<String, Object>> data = productSvc.getFilterNative(filter);
                if(data.size()>0){

                    return new ResponseEntity <List<Map<String, Object>>>( data, HttpStatus.OK);
                } else {

                    return new ResponseEntity <List<Map<String, Object>>>( data, HttpStatus.NO_CONTENT);
                }
                
            }
         catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity <?> update(@RequestBody final Product data){
        try {
                return new ResponseEntity <Product> (productSvc.update(data), HttpStatus.OK);
            
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity <String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

     @GetMapping("/id/{id}")
    public ResponseEntity<?> getBy(@PathVariable int id) {
        try {
           Optional <Product> data = productSvc.getById(id, false);
                if(data.isPresent()){

                    return new ResponseEntity <Product>(data.get(), HttpStatus.OK);
                } else {

                    return new ResponseEntity <Product> (data.get(), HttpStatus.NO_CONTENT);
                }
                
            }
         catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
}
