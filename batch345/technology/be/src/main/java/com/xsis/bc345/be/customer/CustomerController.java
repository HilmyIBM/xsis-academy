package com.xsis.bc345.be.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/customer")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomer() {
        var data = customerService.getAllCustomer();

        if (data.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable long id) {
        return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveCustomer(@RequestBody CustomerModel customerModel) {
        return new ResponseEntity<>(customerService.createCustomer(customerModel), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CustomerModel customerModel) {
        return new ResponseEntity<>(customerService.login(customerModel), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerModel customerModel) {
        return new ResponseEntity<>(customerService.updateCustomer(customerModel), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCustomer(@RequestBody CustomerModel customerModel) {
        return new ResponseEntity<>(customerService.deleteCustomer(customerModel), HttpStatus.OK);
    }
}
