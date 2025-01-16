package com.xsis.bc345.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.backend.models.Category;
import com.xsis.bc345.backend.models.Customer;
import com.xsis.bc345.backend.services.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



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
        return new ResponseEntity<List<Customer>>(
          data,
          data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT
        );
      } catch (Exception e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @PostMapping("")
  public ResponseEntity<?> create(@RequestBody final Customer data) {
      try {
        return new ResponseEntity<Customer>(customerSvc.create(data), HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
  
}
