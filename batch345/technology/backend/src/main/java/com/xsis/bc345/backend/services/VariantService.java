package com.xsis.bc345.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xsis.bc345.backend.models.Variant;
import com.xsis.bc345.backend.repositories.VariantRepository;

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
      throw e;
    }
  }
}
