package com.xsis.bc345.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xsis.bc345.backend.models.OrderHeader;
import com.xsis.bc345.backend.repositories.OrderHeaderRepository;

@Service
public class OrderHeaderService {
  private OrderHeaderRepository orderHeaderRepo;

  
  public OrderHeaderService(OrderHeaderRepository orderHeaderRepo) {
    this.orderHeaderRepo = orderHeaderRepo;
  }


  public List<OrderHeader> getAll() {
    try {
      return orderHeaderRepo.findByDeleted(false).get();
    } catch (Exception e) {
      throw e;
    }
  }
  
}
