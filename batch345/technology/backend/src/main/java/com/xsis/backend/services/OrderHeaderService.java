package com.xsis.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xsis.backend.models.OrderHeader;
import com.xsis.backend.repositories.OrderHeaderRepository;

@Service
public class OrderHeaderService {
    private OrderHeaderRepository orderHeaderRepository;

    public OrderHeaderService(OrderHeaderRepository orderHeaderRepository) {
        this.orderHeaderRepository = orderHeaderRepository;
    }

    public List<OrderHeader> getAll() throws Exception {
        return orderHeaderRepository.findByDeleted(false).get();
    }
}
