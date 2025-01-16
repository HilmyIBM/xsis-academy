package com.xsis.bc345.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.backend.models.OdrDetailModel;
import com.xsis.bc345.backend.models.OdrHeaderModel;
import com.xsis.bc345.backend.services.OdrHeaderService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/odrheader")
public class OdrHeaderController {
    @Autowired
    private OdrHeaderService OdrHeaderSVC;
    
     
    @GetMapping("")
    public ResponseEntity<?>getAll(){
         try {
            List<OdrHeaderModel> data= OdrHeaderSVC.getAll();
            if (data.size()>0) {
                return new ResponseEntity<List<OdrHeaderModel>>(data,HttpStatus.OK);   
            }else{
                return new ResponseEntity<List<OdrHeaderModel>>(data,HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
