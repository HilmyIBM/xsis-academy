package com.xsis.bc345.be.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.OrderDetail;
import com.xsis.bc345.be.models.OrderHeader;
import com.xsis.bc345.be.repositories.OrderDetailRepository;
import com.xsis.bc345.be.repositories.OrderHeaderRepository;

@Service
public class OrderHeaderService {
     private OrderHeaderRepository orderHeaderRepo;

    public OrderHeaderService(OrderDetailRepository OrderDetailRepo) {
        this.orderHeaderRepo = orderHeaderRepo;
    }
    
     public List<OrderHeader> getAll() {
        try {
            return orderHeaderRepo.findByDeleted(false).get();
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }

}
