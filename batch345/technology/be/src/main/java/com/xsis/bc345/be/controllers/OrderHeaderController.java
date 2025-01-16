package com.xsis.bc345.be.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.be.models.OrderHeader;
import com.xsis.bc345.be.services.OrderHeaderService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/order-header")
public class OrderHeaderController {

    @Autowired
    private OrderHeaderService orderHeaderSvc;

    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestParam int id) {
        try {
            List<OrderHeader> data = orderHeaderSvc.getAll(id);

            if (data.isEmpty()) {
                return new ResponseEntity<List<OrderHeader>>(new ArrayList<OrderHeader>(), HttpStatus.OK);
            }

            return new ResponseEntity<List<OrderHeader>>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
