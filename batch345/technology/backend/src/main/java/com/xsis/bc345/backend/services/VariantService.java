package com.xsis.bc345.backend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xsis.bc345.backend.models.Variant;
import com.xsis.bc345.backend.repositories.VariantRepository;

@Service
public class VariantService {
  
  private VariantRepository variantRepo;

  public VariantService(VariantRepository variantRepo){
    this.variantRepo = variantRepo;
  }

  public List<Map<String, Object>> getAllNative() throws Exception {
    return variantRepo.findAllByNativeQuery().get();
  }

  public List<Variant> getAll() {
    try {
      return variantRepo.findByDeleted(false).get();
    } catch (Exception e) {
      throw e;
    }
  }

  public Optional<Map<String, Object>> getByIdNative(int id) throws Exception {
    return variantRepo.findAllByNativeQueryAndId(id);
  }

  public Optional<Variant> getById(int id) throws Exception{
    return variantRepo.findByIdAndDeleted(id, false);
  }

  public Variant create(Variant data) throws Exception {
    return variantRepo.save(data);
  }

  public Variant update(Variant data) throws Exception {
    Optional<Variant> existingVariant = variantRepo.findById(data.getId());
    if(existingVariant.isPresent()) {
      data.setCreateBy(existingVariant.get().getCreateBy());
      data.setCreateDate(existingVariant.get().getCreateDate());
      data.setUpdateDate(LocalDateTime.now());

      return variantRepo.save(data);
    } else {
      throw new Exception("Variant tidak ada");
    }
  }

  public Variant delete(int id, int userId) throws Exception {
    Optional<Variant> existingVariant = variantRepo.findById(id);

    if(existingVariant.isPresent()){
      existingVariant.get().setDeleted(true);
      existingVariant.get().setUpdateBy(userId);
      existingVariant.get().setUpdateDate(LocalDateTime.now());

      return variantRepo.save(existingVariant.get());
    }
    else {
      throw new Exception("Variant tidak ditemukan");
    }
  }

  
}
