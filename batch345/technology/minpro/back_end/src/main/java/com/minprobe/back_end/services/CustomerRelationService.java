package com.minprobe.back_end.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minprobe.back_end.models.BloodGroup;
import com.minprobe.back_end.models.CustomerRelation;
import com.minprobe.back_end.repositories.CustomerRelationRepository;

@Service
public class CustomerRelationService {
    @Autowired
    private CustomerRelationRepository customerRelationRepo;
    private Optional<CustomerRelation> existingCustomerRelation;


    public List<CustomerRelation> getAll(){
        return customerRelationRepo.findByDelete(false).get();
    }

    public Optional<CustomerRelation> getById(int id){
        return customerRelationRepo.findByIdAndDelete(id, false);
    }

    public Optional<List<CustomerRelation>> getByCode(String filter){
        return customerRelationRepo.findByNameContainsIgnoreCaseAndDelete(filter, false);
    }

    public CustomerRelation create(CustomerRelation data) throws Exception{
        return customerRelationRepo.save(data);
    }

    public CustomerRelation update(CustomerRelation data) throws Exception {
        existingCustomerRelation = customerRelationRepo.findById(data.getId());
        if(existingCustomerRelation.isPresent()){
            data.setCreatedBy(existingCustomerRelation.get().getCreatedBy());
            data.setCreatedOn(existingCustomerRelation.get().getCreatedOn());
            data.setModifiedOn(LocalDateTime.now());

            // Update
            return customerRelationRepo.save(data);
        }else{
            throw new Exception("CustomerRelation doesn't exist!");
        }
    }

    public CustomerRelation delete(int id, int userId) throws Exception {
        existingCustomerRelation = customerRelationRepo.findById(id);

        if (existingCustomerRelation.isPresent()) {
            existingCustomerRelation.get().setDelete(true);
            existingCustomerRelation.get().setDeletedBy(userId);
            existingCustomerRelation.get().setDeletedOn(LocalDateTime.now());

            return customerRelationRepo.save(existingCustomerRelation.get());
        } else {
            throw new Exception("Blood Group doesn't exist!");
        }
    }
}
