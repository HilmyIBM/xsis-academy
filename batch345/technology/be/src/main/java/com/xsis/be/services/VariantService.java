package com.xsis.be.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.be.models.Category;
import com.xsis.be.models.Variant;
import com.xsis.be.repositories.VariantRepository;

@Service
public class VariantService {
    @Autowired
    private VariantRepository variantRepo;
    private Optional<Variant> exsistingVariant;
    public List<Variant> getAll() throws Exception{
        return variantRepo.findByDeleted(false).get();
    }

    public List<Map<String,Object>> getAllNative() throws Exception{
        return variantRepo.findByNativeQuery().get();
    }
    public Variant create(Variant data) throws Exception{
        return variantRepo.save(data);
    }
    public Variant update(Variant data) throws Exception{
        exsistingVariant = variantRepo.findById(data.getId());
        if(exsistingVariant.isPresent()){
            // update field
            data.setCreateBy(exsistingVariant.get().getCreateBy());
            data.setCreateDate(exsistingVariant.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());

            // update table
            return variantRepo.save(data);
        }
        throw new Exception("Category doesn't exsist");
    }

    public Optional<Variant> getById(Integer id) throws Exception{
        return variantRepo.findByIdAndDeleted(id, false);
    }

    public List<Map<String,Object>> getByNameOrDescription(String filter) throws Exception{
        return variantRepo.findNameAndDescVariantByNativeQuery(filter).get();
    }

    public Variant delete(int id, int userId) throws Exception{
        exsistingVariant = variantRepo.findById(id);
        if(exsistingVariant.isPresent()){
            exsistingVariant.get().setDeleted(true);
            exsistingVariant.get().setUpdateBy(userId);
            exsistingVariant.get().setUpdateDate(LocalDateTime.now());
            return variantRepo.save(exsistingVariant.get());
        }
        throw new Exception("Category doesn't exsist");
    }
}
