package com.xsis.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xsis.backend.models.Variant;
import com.xsis.backend.repositories.VariantRepository;

@Service
public class VariantService {
    private VariantRepository variantRepository;

    public VariantService(VariantRepository variantRepository) {
        this.variantRepository = variantRepository;
    }

    public List<Variant> getAll() {
        return variantRepository.findByDeleted(false).get();
    }

    public Variant create(Variant data) throws Exception {
        return variantRepository.save(data);
    }

}
