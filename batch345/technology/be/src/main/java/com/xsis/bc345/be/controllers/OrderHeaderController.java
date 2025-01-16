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
import com.xsis.bc345.be.services.OrderHeaderService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/orderheader")
public class OrderHeaderController {
        @Autowired
    private OrderHeaderService orderHeaderSvc;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try {
            List<OrderHeader> data = orderHeaderSvc.getAll();

            if (data.size()>0){
                return new ResponseEntity<List<OrderHeader>>(data, HttpStatus.OK);
                
            } else {
                return new ResponseEntity<List<OrderHeader>>(data, HttpStatus.NO_CONTENT);
                
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
