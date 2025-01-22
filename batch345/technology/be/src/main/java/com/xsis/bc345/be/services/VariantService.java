package com.xsis.bc345.be.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Variant;
import com.xsis.bc345.be.repositories.VariantRepository;

@Service
public class VariantService {
    @Autowired
    private VariantRepository variantRepo;
    private Optional<Variant> existingVariant;

    // public List<Variant> getAll(){
    //     try {
    //         return variantRepo.findByDeleted(false).get();
    //         // return variantRepo.findAllVariantsWithCategoryAndDeleted(false);
    //     } catch (Exception e) {
    //         throw e;
    //     }
    // }

    public List<Map<String, Object>> getAll() throws Exception {
        try {
            return variantRepo.findAllVariant().get();
            // return variantRepo.findAllVariantsWithCategoryAndDeleted(false);
        } catch (Exception e) {
            throw e;
        }
    }

    public Optional<Variant> getById(int id){
        return variantRepo.findByIdAndDeleted(id, false);
    }

    public Variant create(Variant data) throws Exception{
        return variantRepo.save(data);
    }

    public Variant update(Variant data) throws Exception {
        existingVariant = variantRepo.findById(data.getId());
        if(existingVariant.isPresent()){
            data.setCreateBy(existingVariant.get().getCreateBy());
            data.setCreateDate(existingVariant.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());

            // Update
            return variantRepo.save(data);
        }else{
            throw new Exception("Variant doesn't exist!");
        }
    }

    public Variant delete(int id, int userId) throws Exception {
        existingVariant = variantRepo.findById(id);

        if (existingVariant.isPresent()) {
            existingVariant.get().setDeleted(true);
            existingVariant.get().setUpdateBy(userId);
            existingVariant.get().setUpdateDate(LocalDateTime.now());

            return variantRepo.save(existingVariant.get());
        } else {
            throw new Exception("Variant doesn't exist!");
        }
    }

}
