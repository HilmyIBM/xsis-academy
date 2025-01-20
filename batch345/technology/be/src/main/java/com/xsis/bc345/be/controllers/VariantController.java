package com.xsis.bc345.be.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    

    
}
