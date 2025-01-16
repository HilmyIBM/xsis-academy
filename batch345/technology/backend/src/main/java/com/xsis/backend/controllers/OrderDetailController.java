package com.xsis.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.backend.models.OrderDetail;
import com.xsis.backend.services.OrderDetailService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/order-detail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            List<OrderDetail> data = orderDetailService.getAll();
            return new ResponseEntity<List<OrderDetail>>(data,
                    (data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
