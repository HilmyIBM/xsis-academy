package com.xsis.backend.services;

import java.util.List;
import java.util.Optional;

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

    public Optional<Customer> getById(int id) throws Exception {
        return customerRepository.findByIdAndDeleted(id, false);
    }

    public List<Customer> getByFilter(String filter) throws Exception {
        return customerRepository.findNativeByFilter(filter).get();
    }

}
