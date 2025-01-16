package com.xsis.bc345.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xsis.bc345.backend.models.Customer;
import com.xsis.bc345.backend.repositories.CustomerRepository;

@Service
public class CustomerService {
  private CustomerRepository customerRepo;

  public CustomerService(CustomerRepository customerRepo){
    this.customerRepo = customerRepo;
  }

  public List<Customer> getAll() {
    try {
      return customerRepo.findByDeleted(false).get();
    } catch (Exception e) {
      throw e;
    }
  }

}     
