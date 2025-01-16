package com.xsis.bc345.be.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Product;
import com.xsis.bc345.be.models.Variant;
import com.xsis.bc345.be.repositories.VariantRepository;
import com.xsis.bc345.be.repositories.VariantRepository;

@Service
public class VariantService {
    private VariantRepository variantRepo;

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
}
