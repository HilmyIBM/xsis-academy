package com.xsis.bc345.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xsis.bc345.backend.models.OrderDetail;
import com.xsis.bc345.backend.repositories.OrderDetailRepository;

@Service
public class OrderDetailService {
  private OrderDetailRepository orderDetailRepo;

  public OrderDetailService(OrderDetailRepository orderDetailRepo) {
    this.orderDetailRepo = orderDetailRepo;
  }

  public List<OrderDetail> getAll() {
    try {
      return orderDetailRepo.findByDeleted(false).get();
    } catch (Exception e) {
      throw e;
    }
  }
}
