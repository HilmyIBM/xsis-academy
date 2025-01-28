package com.xsis.bc345.backend.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xsis.bc345.backend.models.Customer;
import com.xsis.bc345.backend.repositories.CustomerRepository;

@Service
public class CustomerService {
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

  public CustomerService(CustomerRepository customerRepo){
    this.customerRepo = customerRepo;
  }

  public List<Customer> getAll() {
    try {
      return customerRepo.findByDeleted(false).get();
    } catch (Exception e) {
      throw e;
    }
  }


  ///// URL REFERENCE : www.baeldung.com/sha-256-hashing-java /////
  public Customer create(Customer data) throws Exception {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    String pass = data.getPassword();

    byte[] encodehash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
    data.setPassword(bytesToHex(encodehash));
    return customerRepo.save(data);
  }
  

  public Optional<Customer> getById(int id) {
    return customerRepo.findByIdAndDeleted(id, false);
  }

  public Optional<Customer> getCredentials(String email, String password) throws Exception{
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] encodehash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
    return customerRepo.findByEmailAndPasswordAndDeleted(email, bytesToHex(encodehash), false);
  }

  public Optional<Customer> getByEmail(String email) {
    return customerRepo.findByEmailAndDeleted(email, false);
  }
}     
