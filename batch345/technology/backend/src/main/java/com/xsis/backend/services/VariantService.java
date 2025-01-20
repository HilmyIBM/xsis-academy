package com.xsis.backend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xsis.backend.models.Variant;
import com.xsis.backend.repositories.VariantRepository;

@Service
public class VariantService {
    private VariantRepository variantRepository;
    private Optional<Variant> existingData;

    public VariantService(VariantRepository variantRepository) {
        this.variantRepository = variantRepository;
    }

    public List<Variant> getAll() throws Exception {
        return variantRepository.findByDeleted(false).get();
    }

    public List<Map<String, Object>> getAllNative() throws Exception {
        return variantRepository.findAllNative().get();
    }

    public Variant create(Variant data) throws Exception {
        return variantRepository.save(data);
    }

    public Optional<Variant> getById(int id) throws Exception {
        return variantRepository.findByIdAndDeleted(id, false);
    }

    public Variant update(Variant data) throws Exception {
        existingData = variantRepository.findById(data.getId());
        if (existingData.isPresent()) {
            data.setCreateBy(existingData.get().getCreateBy());
            data.setCreateDate(existingData.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());
            return variantRepository.save(data);
        } else {
            throw new Exception("Variant doesn't exist!");
        }
    }

    public Variant delete(int id, int userId) throws Exception {
        existingData = variantRepository.findById(id);
        if (existingData.isPresent()) {
            existingData.get().setDeleted(true);
            existingData.get().setUpdateBy(userId);
            existingData.get().setUpdateDate(LocalDateTime.now());

            return variantRepository.save(existingData.get());
        } else {
            throw new Exception("Variant doesn't exist!");
        }
    }

}
