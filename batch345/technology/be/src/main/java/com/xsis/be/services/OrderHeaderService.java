package com.xsis.be.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.be.models.OrderHeader;
import com.xsis.be.repositories.OrderHeaderRepository;

@Service
public class OrderHeaderService {
    @Autowired
    private OrderHeaderRepository orderHeaderRepo;
    public List<OrderHeader> getAll() throws Exception{
        return orderHeaderRepo.findByDeleted(false).get();
    }
}
