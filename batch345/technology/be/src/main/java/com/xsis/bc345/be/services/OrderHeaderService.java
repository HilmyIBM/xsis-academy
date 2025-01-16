package com.xsis.bc345.be.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.OrderHeader;
import com.xsis.bc345.be.repositories.OrderHeaderRepository;

@Service
public class OrderHeaderService {
    
    private OrderHeaderRepository orderHeaderRepo;

    public OrderHeaderService(OrderHeaderRepository orderHeaderRepo) {
        this.orderHeaderRepo = orderHeaderRepo;
    }

    public List<OrderHeader> getAll(int id) throws Exception {
        return orderHeaderRepo.findByCustomerId(id).get();
    }
}
