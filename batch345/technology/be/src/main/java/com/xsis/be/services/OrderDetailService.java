package com.xsis.be.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.be.models.OrderDetail;
import com.xsis.be.repositories.OrderDetailRepository;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepo;
    public List<OrderDetail> getAll() throws Exception{
        return orderDetailRepo.findByDeleted(false).get();
    }
}
