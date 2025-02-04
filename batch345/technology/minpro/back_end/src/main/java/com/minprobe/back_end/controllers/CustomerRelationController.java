package com.minprobe.back_end.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minprobe.back_end.models.BloodGroup;
import com.minprobe.back_end.models.CustomerRelation;
import com.minprobe.back_end.services.BloodGroupService;
import com.minprobe.back_end.services.CustomerRelationService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/customer/relation")
public class CustomerRelationController {
    
    @Autowired
    private CustomerRelationService customerRelationSvc;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            List<CustomerRelation> data = customerRelationSvc.getAll();
            return new ResponseEntity<List<CustomerRelation>>(data, data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
