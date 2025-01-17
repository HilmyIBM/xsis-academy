package com.xsis.bc345.be.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Category;
import com.xsis.bc345.be.models.Variant;
import com.xsis.bc345.be.repositories.VariantRepository;

@Service
public class VariantService {
    @Autowired
    private VariantRepository variantRepo;

    public List<Variant> getAll(){
        try {
            return variantRepo.findByDeleted(false).get();
        } catch (Exception e) {
            throw e;
        }
    }

    public Variant create(Variant data) throws Exception{
        return variantRepo.save(data);
    }

}
