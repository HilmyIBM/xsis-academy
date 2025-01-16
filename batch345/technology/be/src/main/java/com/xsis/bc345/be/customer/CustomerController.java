package com.xsis.bc345.be.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllCustomer() {
        var data = customerService.getAllCustomer();

        if (data.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> saveCustomer(@RequestBody CustomerModel customerModel) {
        return new ResponseEntity<>(customerService.createCustomer(customerModel), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerModel customerModel) {
        return new ResponseEntity<>(customerService.updateCustomer(customerModel), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteCustomer(@RequestBody CustomerModel customerModel) {
        customerService.deleteCustomer(customerModel);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
