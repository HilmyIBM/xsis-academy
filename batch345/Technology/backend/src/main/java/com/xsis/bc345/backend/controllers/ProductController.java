package com.xsis.bc345.backend.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @GetMapping("pagination/{page}/{size}")
    public ResponseEntity<?> getpagination(@RequestParam int page,@RequestParam int size){
        try {
            final Page<ProductModel> data=productSVC.getPagination(page, size);
            if (data.getSize()>0) {
                return new ResponseEntity<Page<ProductModel>>(data,HttpStatus.OK);
            } else {
                return new ResponseEntity<Page<ProductModel>>(data,HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/paginated/{page}/{size}")
    public ResponseEntity<?> getAll(@PathVariable int page,@PathVariable int size,@RequestParam(defaultValue = "id") String sort, @RequestParam(defaultValue = "asc") String order){
         try {
            final Page<Map<String,Object>> data= productSVC.getPagination(PageRequest.of(page, size,Sort.Direction.fromString(order),sort));
            return new ResponseEntity<Page<Map<String,Object>>> (data,HttpStatus.OK);   
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    } 

    @GetMapping("/paginated/filter/{filter}/{page}/{size}")
    public ResponseEntity<?> getFilter(@PathVariable int page,@PathVariable int size,@PathVariable String filter){
         try {
            final Page<Map<String,Object>> data= productSVC.getfilterNative(filter,PageRequest.of(page, size,Sort.by("id").ascending()));
            return new ResponseEntity<Page<Map<String,Object>>> (data,HttpStatus.OK);   
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    } 
}
