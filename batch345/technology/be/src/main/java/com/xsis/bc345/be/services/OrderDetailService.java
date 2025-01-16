package com.xsis.bc345.be.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.OrderDetail;
import com.xsis.bc345.be.repositories.OrderDetailRepository;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepo;

    public List<OrderDetail> getAll(){
        try {
            return orderDetailRepo.findByDeleted(false).get();
        } catch (Exception e) {
            throw e;
        }
    }
}
