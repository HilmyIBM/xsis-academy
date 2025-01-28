package com.xsis.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.backend.models.Customer;
import com.xsis.backend.repositories.AuthRepository;

@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;

    public Customer login(String email, String password) throws Exception {
        Customer customer = authRepository.findByEmailAndDeleted(email, false)
                .orElseThrow(() -> new Exception("Email not found"));

        if (password.equals(customer.getPassword())) {
            return customer;
        } else {
            throw new Exception("Invalid password");
        }
    }
}
