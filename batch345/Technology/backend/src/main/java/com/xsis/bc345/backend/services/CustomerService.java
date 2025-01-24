package com.xsis.bc345.backend.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.bc345.backend.models.CustomerModel;
import com.xsis.bc345.backend.repositories.CustomerRepo;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerepo;

    private static String bytestoHex(byte[] hash){
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

    public List<CustomerModel> getAll(){
        try {
            return customerepo.findByisDeleted(false).get();
        } catch (Exception e) {
            throw e;
        }
    }

    public CustomerModel create(CustomerModel data) throws Exception{
        return customerepo.save(data);
    }

    public CustomerModel update(CustomerModel data) throws Exception{
        Optional<CustomerModel>customerexist=customerepo.findById(data.getId());
        if(customerexist.isPresent()){
            if (data.getPassword() != null || !data.getPassword().isEmpty()) {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(data.getPassword().getBytes(StandardCharsets.UTF_8));
                data.setPassword(bytestoHex(hash));
            }else{
                data.setPassword(customerexist.get().getPassword());
            }
            data.setCreateBy(customerexist.get().getCreateBy());
            data.setCreateDate(customerexist.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());
            return customerepo.save(data);
        }else{
            throw new Exception("Data Tidak Ada");
        }

    }

    public Optional<CustomerModel> getbyId(Integer id){
        return customerepo.findByIdAndIsDeleted(id,false);
    }

    public CustomerModel addCustomer(CustomerModel data) throws Exception{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data.getPassword().getBytes(StandardCharsets.UTF_8));
        data.setPassword(bytestoHex(hash));
        return customerepo.save(data);
    }

    public CustomerModel delete(int id,int userId) {
        Optional<CustomerModel> customerExist = customerepo.findById(id);
        if (customerExist.isPresent()) {
            customerExist.get().setIsDeleted(true);
            customerExist.get().setUpdateBy(userId);
            customerExist.get().setUpdateDate(LocalDateTime.now());
            return customerepo.save(customerExist.get());
        } else {
            throw new RuntimeException("Data Tidak Ada");
        }
    }
}
