package com.xsis.bc345.be.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.be.models.OrderHeader;
import com.xsis.bc345.be.services.OrderService;

@RestController
@CrossOrigin("*")
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderSvc;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            List<OrderHeader> data = orderSvc.getAll();
            
            return new ResponseEntity<List<OrderHeader>>(
                data,
                data.size() > 0  ? HttpStatus.OK : HttpStatus.NO_CONTENT
            );
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
