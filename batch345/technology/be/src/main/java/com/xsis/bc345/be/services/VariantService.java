package com.xsis.bc345.be.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Variant;
import com.xsis.bc345.be.repositories.VariantRepositori;

@Service
public class VariantService {
    private VariantRepositori variantRepo;

    public VariantService(VariantRepositori variantRepo){
        this.variantRepo = variantRepo;
    }

    public List<Variant> getAll() throws Exception{
        return variantRepo.findByDeleted(false).get();
    }
            

}
