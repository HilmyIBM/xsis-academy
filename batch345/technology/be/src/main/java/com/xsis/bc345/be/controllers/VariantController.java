package com.xsis.bc345.be.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import com.xsis.bc345.be.models.Category;
import com.xsis.bc345.be.models.Variant;
import com.xsis.bc345.be.models.Variant;
import com.xsis.bc345.be.services.VariantService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/variant")
public class VariantController {
    @Autowired
    private VariantService variantSvc;

    // @GetMapping("")
    // public ResponseEntity<?> getAll(){
    //     try {
    //         List<Variant> data = variantSvc.getAll();

    //         if (data.size()>0){
    //             return new ResponseEntity<List<Variant>>(data, HttpStatus.OK);
                
    //         } else {
    //             return new ResponseEntity<List<Variant>>(data, HttpStatus.NO_CONTENT);
                
    //         }
    //     } catch (Exception e) {
    //         // TODO: handle exception
    //         return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    @GetMapping("")
    public ResponseEntity<?> getAllNative(){
        try {
            final List<Map<String, Object>> data = variantSvc.getAllNative();

            return new ResponseEntity<List<Map<String, Object>>>(data, data.size()>0 ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);

            // if (data.size()>0){
            //     return new ResponseEntity<List<Variant>>(data, HttpStatus.OK);
                
            // } else {
            //     return new ResponseEntity<List<Variant>>(data, HttpStatus.NO_CONTENT);
                
            // }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity <?> create(@RequestBody final Variant data){
        try {
            Variant newVariant = variantSvc.create(data);
                return new ResponseEntity <Variant> (newVariant, HttpStatus.CREATED);
            
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity <String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping("")
    public ResponseEntity <?> update(@RequestBody final Variant data){
        try {
                return new ResponseEntity <Variant> (variantSvc.update(data), HttpStatus.OK);
            
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity <String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getBy(@PathVariable int id) {
        try {
           Optional <Variant> data = variantSvc.getById(id, false);
                if(data.isPresent()){

                    return new ResponseEntity <Variant>(data.get(), HttpStatus.OK);
                } else {

                    return new ResponseEntity <Variant> (data.get(), HttpStatus.NO_CONTENT);
                }
                
            }
         catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/delete/{id}/{userId}")
    public ResponseEntity <?> delete(@PathVariable int id, @PathVariable int userId) {
        try {
            Variant data = variantSvc.delete(id, userId);

            if(data.isDeleted()){
                return new ResponseEntity<Variant>(data, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Failed to delete category", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity <String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getByCategory(@PathVariable int categoryId){
        try {
            final Optional<List<Variant>> data = variantSvc.getByCategory(categoryId);

            return new ResponseEntity<>(
                data.get(), data.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT
            );
        } catch (Exception e) {
            // TODO: handle exception
        }
                return null;
    }
    
}
