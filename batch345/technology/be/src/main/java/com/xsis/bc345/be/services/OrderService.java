package com.xsis.bc345.be.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.OrderHeader;
import com.xsis.bc345.be.repositories.OrderRepository;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepo;

    public List<OrderHeader> getAll() {
        return orderRepo.findAll();
    }
}
