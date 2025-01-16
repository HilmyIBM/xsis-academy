package com.xsis.bc345.be.order.detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/order/detail")
public class OrderDetailController {

    private final OrderDetailService service;

    @Autowired
    public OrderDetailController(OrderDetailService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllOrderDetail() {
        var data = service.getAllOrderDetail();

        if (data.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> saveOrderDetail(@RequestBody OrderDetailModel model) {
        return new ResponseEntity<>(service.createDetailOrder(model), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<?> updateOrderDetail(@RequestBody OrderDetailModel model) {
        return new ResponseEntity<>(service.updateDetailOrder(model), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteOrderDetail(@RequestBody OrderDetailModel model) {
        service.deleteDetailOrder(model);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
