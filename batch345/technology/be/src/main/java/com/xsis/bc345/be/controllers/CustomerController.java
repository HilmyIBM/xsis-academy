package com.xsis.bc345.be.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.be.models.Customer;
import com.xsis.bc345.be.services.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerSvc;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            List<Map<String, Object>> data = customerSvc.getAll();

            return new ResponseEntity<List<Map<String, Object>>>(
                data,
                data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT
            );
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getBy(int id) {
        try {
            Optional<Map<String, Object>> data = customerSvc.getBy(id);

            return new ResponseEntity<Map<String, Object>>(
                data.get(),
                data.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT
            );
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<?> getBy(String filter) {
        try {
            List<Map<String, Object>> data = customerSvc.getBy(filter);

            return new ResponseEntity<List<Map<String, Object>>>(
                data,
                data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT
            );
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Customer data) {
        try {
            return new ResponseEntity<Customer>(customerSvc.create(data), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Customer data) {
        try {
            return new ResponseEntity<Customer>(customerSvc.update(data), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}/{userId}")
    public ResponseEntity<?> delete(
        @PathVariable int id,
        @PathVariable int userId
    ) {
        try {
            final Customer data = customerSvc.delete(id, userId);

            if (data.isDeleted()){
                return new ResponseEntity<Customer> (data, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<String>("Failed to delete Customer with ID = (" + id + ")", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
