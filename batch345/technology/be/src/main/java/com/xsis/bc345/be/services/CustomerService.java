package com.xsis.bc345.be.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Customer;
import com.xsis.bc345.be.repositories.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepo;

    public List<Customer> getAll(){
        try {
            return customerRepo.findByDeleted(false).get();
        } catch (Exception e) {
            throw e;
        }
    }
}
