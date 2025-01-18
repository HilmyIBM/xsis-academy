package com.xsis.bc345.be.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Variant;
import com.xsis.bc345.be.repositories.VariantRepository;

@Service
public class VariantService {
    @Autowired
    private VariantRepository variantRepo;

    private Optional<Variant> existingVariant;

    public List<Variant> getAll() throws Exception {
        return variantRepo.findByDeleted(false).get();
    }

    public Optional<Variant> getBy(int id) throws Exception {
        return variantRepo.findByIdAndDeleted(id, false);
    }

    public Optional<List<Variant>> getBy(String filter) throws Exception {
        return variantRepo.findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(filter, filter, false);
    }

    public Optional<List<Variant>> getByName(String name) throws Exception {
        return variantRepo.findByNameContainsIgnoreCaseAndDeleted(name, false);
    }

    public Variant create(Variant data) {
        return variantRepo.save(data);
    }

    public Variant update(Variant data) throws Exception {
        existingVariant = variantRepo.findByIdAndDeleted(data.getId(), false);

        if (existingVariant.isPresent()) {
            //Update new data
            data.setCreateBy(existingVariant.get().getCreateBy());
            data.setCreateDate(existingVariant.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());

            return variantRepo.save(data);
        }
        else {
            throw new Exception("Variant doesn't exist!");
        }
    }

    public Variant delete(int id, int userId) throws Exception {
        existingVariant = variantRepo.findByIdAndDeleted(userId, false);

        if (existingVariant.isPresent()){
            existingVariant.get().setDeleted(true);
            existingVariant.get().setUpdateBy(userId);
            existingVariant.get().setUpdateDate(LocalDateTime.now());

            return variantRepo.save(existingVariant.get());
        }
        else {
            throw new Exception("Variant doesm't exist!");
        }
    }
}
