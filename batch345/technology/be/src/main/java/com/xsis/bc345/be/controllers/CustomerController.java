package com.xsis.bc345.be.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.be.models.Customer;
import com.xsis.bc345.be.services.CustomerService;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin("*")
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerSvc;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            List<Customer> data = customerSvc.getAll();

            if (data.isEmpty()){
                new ResponseEntity<List<Customer>>(new ArrayList<Customer>(),HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<Customer>>(data,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
