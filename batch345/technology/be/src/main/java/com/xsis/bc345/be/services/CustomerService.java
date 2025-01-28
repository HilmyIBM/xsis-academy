package com.xsis.bc345.be.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Customer;
import com.xsis.bc345.be.repositories.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepo;

    //Hash SHA-256
	private static String stringToHex(String strInput) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashByte = digest.digest(strInput.getBytes(StandardCharsets.UTF_8));

	    StringBuilder hexString = new StringBuilder(2 * hashByte.length);
	    for (int i = 0; i < hashByte.length; i++) {
	        String hex = Integer.toHexString(0xff & hashByte[i]);
	        if(hex.length() == 1) {
	            hexString.append('0');
	        }
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}

    public List<Map<String, Object>> getAll(){
        return customerRepo.findAllNative().get();
    }

    public Optional<Customer> getByEmail(String email) {
        return customerRepo.findByEmailAndDeleted(email.toLowerCase(),false);
    }

    public Optional<Map<String, Object>> getBy(int id) {
        return customerRepo.findByIdNative(id);
    };

    public List<Map<String, Object>> getBy(String filter) {
        return customerRepo.findByFilterNative(filter.toLowerCase()).get();
    }

    public Customer create(Customer data) throws Exception {
        data.setPassword(stringToHex(data.getPassword()));

        return customerRepo.save(data);
    }

    public Customer update(Customer data) throws Exception {
        Optional<Customer> existingCustomer = customerRepo.findByIdAndDeleted(data.getId(), false);

        if (existingCustomer.isPresent()) {
            data.setPassword(stringToHex(data.getPassword()));
            data.setUpdateDate(LocalDateTime.now());

            data.setCreateBy(existingCustomer.get().getCreateBy());
            data.setCreateDate(existingCustomer.get().getCreateDate());

            return customerRepo.save(data);
        }
        else {
            throw new Exception("Customer doesn't exist!");
        }
    }

    public Customer delete(int id, int userId) throws Exception {
        Optional<Customer> existingCustomer = customerRepo.findByIdAndDeleted(id, false);

        if (existingCustomer.isPresent()) {
            existingCustomer.get().setDeleted(true);
            existingCustomer.get().setUpdateBy(userId);
            existingCustomer.get().setUpdateDate(LocalDateTime.now());

            return customerRepo.save(existingCustomer.get());
        }
        else{
            throw new Exception("Customer doesn't exist!");
        }
    };
}
