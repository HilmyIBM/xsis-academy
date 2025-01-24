package com.xsis.be.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
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
    public Customer create(Customer data) throws Exception{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String pass = data.getPassword();
        byte[] hashing = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
        pass = bytesToHex(hashing);
        data.setPassword(pass);
        return customerRepo.save(data);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
