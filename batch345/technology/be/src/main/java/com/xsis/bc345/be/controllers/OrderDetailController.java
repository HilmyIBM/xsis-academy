package com.xsis.bc345.be.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.be.models.OrderDetail;
import com.xsis.bc345.be.services.OrderDetailService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin("*")
@RequestMapping("api/order-detail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailSvc;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            List<OrderDetail> data = orderDetailSvc.getAll();

            if (data.isEmpty()) {
                return new ResponseEntity<List<OrderDetail>>(new ArrayList<OrderDetail>(), HttpStatus.OK);
            }
            return new ResponseEntity<List<OrderDetail>>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
