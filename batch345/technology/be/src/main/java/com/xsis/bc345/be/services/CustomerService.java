package com.xsis.bc345.be.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Customer;
import com.xsis.bc345.be.repositories.CustomerRepository;

@Service
public class CustomerService {
    private CustomerRepository customerRepo;

    public CustomerService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public List<Customer> getAll() throws Exception{
        return customerRepo.findByDeleted(false).get();
    }

    public Customer create(Customer data) throws Exception{
        return customerRepo.save(data);
    }
}
