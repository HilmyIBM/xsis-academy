package com.xsis.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.backend.models.Customer;
import com.xsis.backend.services.AuthService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")  
    public ResponseEntity<?> login(@RequestBody Customer data) {
        try {
            Customer customer = authService.login(data.getEmail(), data.getPassword());

            return new ResponseEntity<Customer>(customer, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>("{\"error\":\"Invalid Email or Password\"}",
                    HttpStatus.UNAUTHORIZED);
        }
    }
}
