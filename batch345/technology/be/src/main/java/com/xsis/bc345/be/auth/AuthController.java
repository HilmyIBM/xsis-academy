package com.xsis.bc345.be.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService service;

    @Autowired
    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody AuthDTO loginData) {
        return new ResponseEntity<>(service.login(loginData), HttpStatus.OK);
    }
}
