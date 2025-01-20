package com.xsis.bc345.be.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Variant;
import com.xsis.bc345.be.repositories.VariantRepository;

@Service
public class VariantService {
    private VariantRepository variantRepo;
    private Optional <Variant> variantExisting;


    public VariantService(VariantRepository variantRepo){
        this.variantRepo = variantRepo;
    }
    
    public List<Variant> getAll() {
        try {
            return variantRepo.findByDeleted(false).get();
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }

    public Variant create(Variant data) throws Exception {
            return variantRepo.save(data);
            
        
    }

    
    public Variant update(Variant data) throws Exception{
       Optional <Variant> variantExisting = variantRepo.findById(data.getId());
        if (variantRepo.findById(data.getId()).isPresent()){
            // update fields
            data.setCreateBy(variantExisting.get().getCreateBy()); 
            data.setCreateDate(variantExisting.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());

            // update table
            return variantRepo.save(data);
        } else {

            // TODO Auto-generated method stub
            throw new Exception("Variant doesn't exist!");
        }
    }

    public Variant delete(int id, int userId) throws Exception{
        variantExisting = variantRepo.findById(id);
        
       if(variantExisting.isPresent()){
        variantExisting.get().setDeleted(true);
        variantExisting.get().setUpdateBy(userId);
        variantExisting.get().setUpdateDate(LocalDateTime.now());
        return variantRepo.save(variantExisting.get());
       } else {
        throw new Exception("doesnt exist");
       }
    }

    public List<Variant> getByDescriptionContainsIgnoreCaseAndDeleted(String description, boolean deleted) {
        // TODO Auto-generated method stub
        return variantRepo.findByDescriptionContainsIgnoreCaseAndDeleted(description, false).get();
    }

    public List<Variant> getByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(String filter,
            boolean deleted) {
        // TODO Auto-generated method stub
        return variantRepo.findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(filter, filter, deleted).get();
    }

    public List<Variant> getByNameIgnoreCaseAndDeleted(String name, boolean deleted) {
        // TODO Auto-generated method stub
        return variantRepo.findByNameIgnoreCaseAndDeleted(name, false).get();
    }

    public Optional<Variant> getById(int id, boolean deleted) {
        // TODO Auto-generated method stub
        return variantRepo.findByIdAndDeleted(id, deleted);    }

    public List<Map<String, Object>> getAllNative() throws Exception {
        // TODO Auto-generated method stub
        return variantRepo.findAllNative().get();   }
    
    

    
}
