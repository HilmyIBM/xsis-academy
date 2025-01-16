package com.xsis.bc345.be.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Order;
import com.xsis.bc345.be.repositories.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;

    public List<Order> getAll(){
        try {
            return orderRepo.findByDeleted(false).get();
        } catch (Exception e) {
            throw e;
        }
    }
}
