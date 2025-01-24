package com.xsis.bc345.be.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Customer;
import com.xsis.bc345.be.repositories.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepo;

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

    public List<Customer> getAll(){
        try {
            return customerRepo.findByDeleted(false).get();
        } catch (Exception e) {
            throw e;
        }
    }

    public Customer create(Customer data) throws Exception {
        // Hash
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String pass = data.getPassword();
        byte[] encodehash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
        System.out.println(encodehash);
        data.setPassword(bytesToHex(encodehash));
        return customerRepo.save(data);
    }
}
