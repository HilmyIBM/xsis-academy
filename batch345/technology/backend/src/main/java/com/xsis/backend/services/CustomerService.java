package com.xsis.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xsis.backend.models.Customer;
import com.xsis.backend.repositories.CustomerRepository;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAll() throws Exception {
        return customerRepository.findByDeleted(false).get();
    }

}
