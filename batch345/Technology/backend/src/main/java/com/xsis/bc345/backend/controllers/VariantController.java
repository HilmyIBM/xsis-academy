package com.xsis.bc345.backend.controllers;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.backend.models.CategoryModel;
import com.xsis.bc345.backend.models.CustomerModel;
import com.xsis.bc345.backend.models.ProductModel;
import com.xsis.bc345.backend.models.VariantModel;
import com.xsis.bc345.backend.services.VariantService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/variant")
public class VariantController {
    @Autowired
    private VariantService variantSVC;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
         try {
            List<VariantModel> data= variantSVC.getAll();
            if (data.size()>0) {
                return new ResponseEntity<List<VariantModel>>(data,HttpStatus.OK);   
            }else{
                return new ResponseEntity<List<VariantModel>>(data,HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }  

    @PostMapping("")
    public ResponseEntity<?> addVariant(@RequestBody final VariantModel data) {
        try {
            return new ResponseEntity<VariantModel>(variantSVC.create(data),HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> updateVariant(@RequestBody final VariantModel data){
        try {
            return new ResponseEntity<VariantModel>(variantSVC.update(data),HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getbyId(@PathVariable int id){
        try {
            Optional<VariantModel> data=variantSVC.getbyId(id);

            if (data.isPresent()) {
                return new ResponseEntity<VariantModel>(data.get(),HttpStatus.OK);   
            }else{
                return new ResponseEntity<VariantModel>(data.get(),HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
    }
    

}
