package com.xsis.bc345.backend.services;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.bc345.backend.models.CategoryModel;
import com.xsis.bc345.backend.models.CustomerModel;
import com.xsis.bc345.backend.repositories.CustomerRepo;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerepo;

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
            data.setCreateBy(customerexist.get().getCreateBy());
            data.setCreateDate(customerexist.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());
            return customerepo.save(data);
        }else{
            throw new Exception("Data Tidak Ada");
        }

    }
}
