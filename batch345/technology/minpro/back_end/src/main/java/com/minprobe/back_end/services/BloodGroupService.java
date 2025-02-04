package com.minprobe.back_end.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minprobe.back_end.models.BloodGroup;
import com.minprobe.back_end.repositories.BloodGroupRepository;

@Service
public class BloodGroupService {
    @Autowired
    private BloodGroupRepository bloodGroupRepo;
    private Optional<BloodGroup> existingBloodGroup;

    public List<BloodGroup> getAll(){
        return bloodGroupRepo.findByDelete(false).get();
    }

    public BloodGroup create(BloodGroup data) throws Exception{
        return bloodGroupRepo.save(data);
    }

    public Optional<BloodGroup> getById(int id){
        return bloodGroupRepo.findByIdAndDelete(id, false);
    }

    public Optional<List<BloodGroup>> getByCode(String filter){
        return bloodGroupRepo.findByCodeContainsIgnoreCaseAndDelete(filter, false);
    }

    public BloodGroup update(BloodGroup data) throws Exception {
        existingBloodGroup = bloodGroupRepo.findById(data.getId());
        if(existingBloodGroup.isPresent()){
            data.setCreatedBy(existingBloodGroup.get().getCreatedBy());
            data.setCreatedOn(existingBloodGroup.get().getCreatedOn());
            data.setModifiedOn(LocalDateTime.now());

            // Update
            return bloodGroupRepo.save(data);
        }else{
            throw new Exception("BloodGroup doesn't exist!");
        }
    }

    public BloodGroup delete(int id, int userId) throws Exception {
        existingBloodGroup = bloodGroupRepo.findById(id);

        if (existingBloodGroup.isPresent()) {
            existingBloodGroup.get().setDelete(true);
            existingBloodGroup.get().setDeletedBy(userId);
            existingBloodGroup.get().setDeletedOn(LocalDateTime.now());

            return bloodGroupRepo.save(existingBloodGroup.get());
        } else {
            throw new Exception("Blood Group doesn't exist!");
        }
    }
    
}
