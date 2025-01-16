package com.xsis.be.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.be.models.OrderHeader;
import com.xsis.be.services.OrderHeaderService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin("*")
@RequestMapping("api/order-header")
public class OrderHeaderController {
    @Autowired
    private OrderHeaderService orderHeaderSvc;
    @GetMapping("")
    public ResponseEntity<?> getAll() {
       try {
        List<OrderHeader> data = orderHeaderSvc.getAll();
         return new ResponseEntity<List<OrderHeader>>(data, data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
       } catch (Exception e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    
}
