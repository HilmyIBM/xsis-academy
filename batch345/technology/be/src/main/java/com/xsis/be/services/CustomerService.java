package com.xsis.be.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.be.models.Customer;
import com.xsis.be.repositories.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepo;
    public List<Customer> getAll() throws Exception{
       return customerRepo.findByDeleted(false).get();
    }
}
