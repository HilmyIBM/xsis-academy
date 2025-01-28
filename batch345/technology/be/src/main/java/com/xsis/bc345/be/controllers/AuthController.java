package com.xsis.bc345.be.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.be.models.Auth;
import com.xsis.bc345.be.models.Customer;
import com.xsis.bc345.be.services.CustomerService;
import com.xsis.bc345.be.models.ApiResponse;

@RestController
@CrossOrigin("*")
@RequestMapping("api/auth")
public class AuthController {

    private CustomerService customerSvc;

    public AuthController(CustomerService customerSvc) {
        this.customerSvc = customerSvc;
    }

    // RequestBody just only 1 parameter, creating the Models representing Auth
    // Error when using (@RequestBody String email, @RequestBody String password)
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody Auth loginRequest) {
        try {
            Customer existingUser = customerSvc.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
            if (existingUser == null) {
                return new ResponseEntity<String>("Customer Not Found", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<Customer>(existingUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("An error occuring while processing login",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
