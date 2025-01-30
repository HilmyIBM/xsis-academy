package com.xsis.bc345.be.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Customer;
import com.xsis.bc345.be.repositories.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepo;

    public List<Customer> getAll() throws Exception {
        return customerRepo.findByDeleted(false).get();
    }

    public List<Customer> getByNameOrEmail(String filter) throws Exception {
        return customerRepo.findByEmailContainsIgnoreCaseOrNameContainsIgnoreCaseAndDeleted(filter, filter, false).get();
    }

    public Customer create(Customer data) throws Exception {
        String password = stringToHex(data.getPassword());
        data.setCreateBy(100);
        data.setPassword(password);
        return customerRepo.save(data);
    }

    public Customer authenticateUser(String email, String inputPassword) throws Exception {
        Customer user = customerRepo.findByEmailIgnoreCaseAndDeleted(email, false).orElse(null);

        if (user == null) {
            return null;
        }
        String hashedPasswordInput = stringToHex(inputPassword);
        if (user.getPassword().equals(hashedPasswordInput)) {
            return user;
        } else {
            return null;
        }
    }

    // Hash SHA-256
    private String stringToHex(String strInput) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashByte = digest.digest(strInput.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder(2 * hashByte.length);
        for (int i = 0; i < hashByte.length; i++) {
            String hex = Integer.toHexString(0xff & hashByte[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
