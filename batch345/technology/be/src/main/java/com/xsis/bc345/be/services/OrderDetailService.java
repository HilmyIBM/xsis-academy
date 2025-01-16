package com.xsis.bc345.be.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Category;
import com.xsis.bc345.be.models.OrderDetail;
import com.xsis.bc345.be.repositories.OrderDetailRepository;

@Service
public class OrderDetailService {
    private OrderDetailRepository orderDetailRepo;

    public OrderDetailService(OrderDetailRepository OrderDetailRepo) {
        this.orderDetailRepo = orderDetailRepo;
    }
    
     public List<OrderDetail> getAll() {
        try {
            return orderDetailRepo.findByDeleted(false).get();
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }

}
