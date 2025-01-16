package com.xsis.be.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.be.models.Variant;
import com.xsis.be.repositories.VariantRepository;

@Service
public class VariantService {
    @Autowired
    private VariantRepository variantRepo;
    public List<Variant> getAll() throws Exception{
        return variantRepo.findByDeleted(false).get();
    }
}
