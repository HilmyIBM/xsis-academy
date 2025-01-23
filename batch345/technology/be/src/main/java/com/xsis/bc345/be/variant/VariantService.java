package com.xsis.bc345.be.variant;

import com.xsis.bc345.be.category.CategoryRepository;
import com.xsis.bc345.be.product.ProductRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VariantService {

    private final VariantRepository variantRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public VariantService(VariantRepository variantRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.variantRepository = variantRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<VariantModel> getAllVariant(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<VariantModel> pageVariant = variantRepository.findAllByDeleted(false, pageable);

        return pageVariant.getContent();
    }

    public List<VariantModel> getAllVariant() {
        return variantRepository
                .findAllByDeletedAndCategory_Deleted(false, false)
                .orElse(List.of());
    }

    public List<VariantModel> getAllByCategory(int id) {
        var data = variantRepository
                .nativeFindAllVariantsByCategoryId(id);

        if (data.isEmpty())
            return List.of();

        return data.get()
                .stream()
                .map(v -> {
                    VariantModel mod = new VariantModel();

                    mod.setName(v.getName());
                    mod.setId(v.getId());

                    return mod;
                }).collect(Collectors.toCollection(ArrayList::new));
    }

    public VariantModel getById(int id, boolean deleted) {
        var data = variantRepository.findByIdAndDeleted(id, deleted);

        if (data.isEmpty())
            throw new EntityNotFoundException("Variant with id %s doesn't exists".formatted(id));

        return data.get();
    }

    public VariantModel createVariant(VariantModel variantModel) {
        return variantRepository.save(variantModel);
    }

    public VariantModel updateVariant(VariantModel variantModel) {
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
    }

    public VariantModel deleteVariant(VariantModel variantModel) {
        var data = variantRepository.findByIdAndDeleted(variantModel.getId(), false);
        int relatedProducts = productRepository.countAllByVariant_DeletedAndId(false, variantModel.getId());

        if (data.isEmpty())
            throw new EntityNotFoundException("Variant with id %s doesn't exists".formatted(variantModel.getId()));

        if (relatedProducts > 0)
            throw new EntityExistsException("Cannot delete variant with id %s because they are used by products".formatted(variantModel.getId()));

        VariantModel existingData = data.get();

        existingData.setUpdateDate(LocalDateTime.now());
        existingData.setUpdateBy(variantModel.getUpdateBy());
        existingData.setDeleted(true);

        return variantRepository.save(existingData);
    }
}
