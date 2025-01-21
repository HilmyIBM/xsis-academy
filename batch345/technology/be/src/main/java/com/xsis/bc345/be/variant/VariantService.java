package com.xsis.bc345.be.variant;

import com.xsis.bc345.be.category.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VariantService {

    private final VariantRepository variantRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public VariantService(VariantRepository variantRepository, CategoryRepository categoryRepository) {
        this.variantRepository = variantRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<VariantModel> getAllVariant() {
        try {
            var data = variantRepository.findAllByDeletedAndCategory_Deleted(false, false);

            return data.orElse(List.of());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public VariantModel getById(int id, boolean deleted) {
        try {
            var data = variantRepository.findByIdAndDeleted(id, deleted);

            if (data.isEmpty())
                throw new EntityNotFoundException("Variant with id %s doesn't exists".formatted(id));

            return data.get();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public VariantModel createVariant(VariantModel variantModel) {
        try {
            return variantRepository.save(variantModel);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public VariantModel updateVariant(VariantModel variantModel) {
        try {
            var variant = variantRepository.findByIdAndDeleted(variantModel.getId(), false);

            if (variant.isEmpty())
                throw new EntityNotFoundException("Variant with id %s doesn't exists or already deleted".formatted(variantModel.getId()));

            var category = categoryRepository.findByIdAndDeleted(variantModel.getCategory().getId(), false);

            if (category.isEmpty())
                throw new EntityNotFoundException("Category with id %s doesn't exists or already deleted".formatted(variantModel.getCategory().getId()));

            VariantModel existingData = variant.get();

            variantModel.setCreateBy(existingData.getCreateBy());
            variantModel.setCreateDate(existingData.getCreateDate());
            variantModel.setUpdateDate(LocalDateTime.now());

            return variantRepository.save(variantModel);
        } catch (Exception e)  {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public VariantModel deleteVariant(VariantModel variantModel) {
        try {
            var data = variantRepository.findByIdAndDeleted(variantModel.getId(), false);

            if (data.isEmpty())
                throw new EntityNotFoundException("Variant with id %s doesn't exists".formatted(variantModel.getId()));

            VariantModel existingData = data.get();

            existingData.setUpdateDate(LocalDateTime.now());
            existingData.setUpdateBy(variantModel.getUpdateBy());
            existingData.setDeleted(true);

            return variantRepository.save(existingData);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }
}
