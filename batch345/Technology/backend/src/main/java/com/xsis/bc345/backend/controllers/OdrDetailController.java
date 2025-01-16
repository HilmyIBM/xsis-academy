package com.xsis.bc345.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.backend.models.CustomerModel;
import com.xsis.bc345.backend.models.OdrDetailModel;
import com.xsis.bc345.backend.services.OdrDetailService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/odrDetail")
public class OdrDetailController {
    @Autowired
    private OdrDetailService odrSVC;

    
    @GetMapping("")
    public ResponseEntity<?>getAll(){
         try {
            List<OdrDetailModel> data= odrSVC.getAll();
            if (data.size()>0) {
                return new ResponseEntity<List<OdrDetailModel>>(data,HttpStatus.OK);   
            }else{
                return new ResponseEntity<List<OdrDetailModel>>(data,HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addOdrDetail(@RequestBody final OdrDetailModel data){
          try {
            return new ResponseEntity<OdrDetailModel>(odrSVC.create(data),HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }   
    }

}
