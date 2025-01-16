package com.xsis.bc345.be.order.header;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/order/header")
public class OrderHeaderController {

    private final OrderHeaderService service;

    @Autowired
    public OrderHeaderController(OrderHeaderService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllOrderHeader() {
        var data = service.getAllOrderHeader();

        if (data.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createOrderHeader(@RequestBody OrderHeaderModel model) {
        return new ResponseEntity<>(service.createOrderHeader(model), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<?> updateOrderHeader(@RequestBody OrderHeaderModel model) {
        return new ResponseEntity<>(service.updateOrderHeader(model), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteOrderHeader(@RequestBody OrderHeaderModel model) {
        service.deleteOrderHeader(model);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
