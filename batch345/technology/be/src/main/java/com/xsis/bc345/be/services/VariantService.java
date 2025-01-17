package com.xsis.bc345.be.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Variant;
import com.xsis.bc345.be.repositories.VariantRepository;

@Service
public class VariantService {
     @Autowired
    private VariantRepository variantRepo;

    public List<Variant> getAll() throws Exception {
        return variantRepo.findByDeleted(false).get();
    }

    public Variant create(Variant data) {
        return variantRepo.save(data);
    }

    public Variant update(Variant data) throws Exception {
        Optional<Variant> existingVariant = variantRepo.findById(data.getId());

        if (existingVariant.isPresent()) {
            //Update new data
            data.setCreateBy(existingVariant.get().getCreateBy());
            data.setCreateDate(existingVariant.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());

            return variantRepo.save(data);
        }
        else {
            throw new Exception("Variant doesn't exist!");
        }
    }
            
}
