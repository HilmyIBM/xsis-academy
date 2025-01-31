package com.xsis.batch345.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xsis.batch345.backend.model.Category;
import com.xsis.batch345.backend.model.Variant;
import com.xsis.batch345.backend.repository.VariantRepository;

@Service
public class VariantService {
  
  private VariantRepository variantRepo;
  private Optional<Variant> existingVariant;

  public VariantService( VariantRepository variantRepo ) {
    this.variantRepo = variantRepo;
  }

  public Optional<List<Variant>> findAll() {
    return variantRepo.nativeFindAll();
  }

  public Optional<Variant> findById( Integer id ) {
    return variantRepo.nativeFindById(id);
  }

}
