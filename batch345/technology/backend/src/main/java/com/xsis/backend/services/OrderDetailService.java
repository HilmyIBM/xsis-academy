package com.xsis.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xsis.backend.models.OrderDetail;
import com.xsis.backend.repositories.OrderDetailRepository;

@Service
public class OrderDetailService {
    private OrderDetailRepository orderDetailRepository;

    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<OrderDetail> getAll() throws Exception {
        return orderDetailRepository.findByDeleted(false).get();
    }
}
