package com.xsis.backend.services;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.xsis.backend.models.Customer;
import com.xsis.backend.repositories.CustomerRepository;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    private Optional<Customer> existingData;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean login(String email, String password) {
        Optional<Customer> customer = customerRepository.findByEmail(email);

        return customer.isPresent() && customer.get().getPassword().equals(password);
    }

    public List<Customer> getAll() throws Exception {
        return customerRepository.findByDeleted(false).get();
    }

    public Customer create(Customer data) throws Exception {
        String password = data.getPassword();
        String hashPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
        data.setPassword(hashPassword);
        return customerRepository.save(data);
    }

    public Optional<Customer> getById(int id) throws Exception {
        return customerRepository.findByIdAndDeleted(id, false);
    }

    public List<Map<String, Object>> getListByFilter(String filter) throws Exception {
        return customerRepository.findNativeByFilter(filter).get();
    }

    public Customer update(Customer data) throws Exception {
        existingData = customerRepository.findById(data.getId());
        if (existingData.isPresent()) {
            data.setCreateBy(existingData.get().getCreateBy());
            data.setCreateDate(existingData.get().getCreateDate());
            data.setUpdateBy(existingData.get().getUpdateBy());
            return customerRepository.save(data);
        } else {
            throw new Exception("Customer doesn't exist!");
        }
    }

    public Customer delete(int id, int userId) throws Exception {
        existingData = customerRepository.findById(id);
        if (existingData.isPresent()) {
            existingData.get().setDeleted(true);
            existingData.get().setUpdateBy(userId);
            existingData.get().setUpdateDate(LocalDateTime.now());

            return customerRepository.save(existingData.get());
        } else {
            throw new Exception("Customer doesn't exist!");
        }
    }

}
