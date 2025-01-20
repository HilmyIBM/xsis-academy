package com.xsis.bc345.be.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Variant;
import com.xsis.bc345.be.repositories.VariantRepository;

@Service
public class VariantService {
    private VariantRepository variantRepo;
    private Optional<Variant> existingVariant;

    public VariantService(VariantRepository variantRepo) {
        this.variantRepo = variantRepo;
    }

    public List<Variant> getAll() throws Exception {
        return variantRepo.findByDeleted(false);
    }

    public Variant create(Variant data) throws Exception {
        return variantRepo.save(data);
    }

    public Optional<Variant> getById(int id) {
        return variantRepo.findByIdAndDeleted(id, false);
    }

    public Optional<Map<String,Object>> getByIdNative(int id){
        return variantRepo.findByIdNative(id);
    }

    public Variant update(Variant data) throws Exception {
        existingVariant = variantRepo.findByIdAndDeleted(data.getId(), false);
        if (existingVariant.isPresent()) {
            // updating variant fields
            data.setCreateBy(existingVariant.get().getCreateBy());
            data.setCreateDate(existingVariant.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());

            return variantRepo.save(data);
        }
        throw new Exception("Variant doesn't exists");
    }

    public Variant delete(int id, int userId) throws Exception {
        existingVariant = variantRepo.findById(id);
        if (existingVariant.isPresent()) {
            existingVariant.get().setDeleted(true);
            existingVariant.get().setUpdateBy(userId);
            existingVariant.get().setUpdateDate(LocalDateTime.now());
            return variantRepo.save(existingVariant.get());
        } else {
            throw new Exception("Variant doesn't exists!");
        }
    }

    public List<Map<String,Object>> getAllNative() throws Exception{
        return variantRepo.findAllNative().get();
    }
}
