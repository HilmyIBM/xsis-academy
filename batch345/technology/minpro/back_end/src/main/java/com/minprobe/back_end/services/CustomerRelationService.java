package com.minprobe.back_end.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minprobe.back_end.models.BloodGroup;
import com.minprobe.back_end.models.CustomerRelation;
import com.minprobe.back_end.repositories.CustomerRelationRepository;

@Service
public class CustomerRelationService {
    @Autowired
    private CustomerRelationRepository customerRelationRepo;

    public List<CustomerRelation> getAll(){
        return customerRelationRepo.findByDelete(false).get();
    }
}
