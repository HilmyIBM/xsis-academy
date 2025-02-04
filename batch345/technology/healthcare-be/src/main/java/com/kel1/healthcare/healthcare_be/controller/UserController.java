package com.kel1.healthcare.healthcare_be.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kel1.healthcare.healthcare_be.services.UserService;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userSvc;
    @GetMapping("")
    public ResponseEntity<?> findallnative() {
        try {
            List<Map<String, Object>> data = userSvc.getAllNative();

            return new ResponseEntity<List<Map<String, Object>>>(
                data,
                data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT
            );
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
