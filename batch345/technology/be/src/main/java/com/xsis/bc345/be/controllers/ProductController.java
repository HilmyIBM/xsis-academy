package com.xsis.bc345.be.controllers;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/paginated/{page}/{size}")
    public ResponseEntity<?> getAllNative(@PathVariable int page, @PathVariable int size, @RequestParam(defaultValue = "stock") String sort, @RequestParam(defaultValue = "desc") String sortDirection){
        try {
            final Page<Map<String, Object>> data = productSvc.getAllNative(PageRequest.of(page, size, Sort.by(Direction.fromString(sortDirection), sort)));

                return new ResponseEntity <Page<Map<String, Object>>>(data, HttpStatus.OK);
                
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

    @GetMapping("/paginated/filter/{filter}/{page}/{size}")
    public ResponseEntity<?> getByFilterNative(@PathVariable String filter, @PathVariable int page, @PathVariable int size, @RequestParam(defaultValue = "name") String sort, @RequestParam(defaultValue = "ASC") String sortDirection) {
        try {
           Page<Map<String, Object>> data = productSvc.getFilterNative(PageRequest.of(page, size, Sort.by(Direction.fromString(sortDirection), sort)) ,filter);

                    return new ResponseEntity <Page<Map<String, Object>>>( data, HttpStatus.OK);

                
            }
         catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity <?> create(@RequestBody final Product data){
        try {
            Product newProduct = productSvc.create(data);
                return new ResponseEntity <Product> (newProduct, HttpStatus.CREATED);
            
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity <String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

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
           Map<String, Object> data = productSvc.getByIdNative(id);
                if(data.size()>0){

                        return new ResponseEntity <Map<String, Object>>( data, HttpStatus.OK);
                } else {

                    return new ResponseEntity <Map<String, Object>> (data, HttpStatus.NO_CONTENT);
                }
                
            }
         catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
