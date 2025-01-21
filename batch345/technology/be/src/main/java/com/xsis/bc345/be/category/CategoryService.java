package com.xsis.bc345.be.category;

import com.xsis.bc345.be.variant.VariantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);
    private final CategoryRepository categoryRepository;
    private final VariantRepository variantRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, VariantRepository variantRepository) {
        this.categoryRepository = categoryRepository;
        this.variantRepository = variantRepository;
    }

    public List<CategoryModel> getAll() {
        try {
            var data = categoryRepository.findAllByDeleted(false);

            return data.orElse(List.of());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public CategoryModel getById(int id, boolean deleted) {
        try {
            var data = categoryRepository
                    .findByIdAndDeleted(id, deleted);

            if (data.isEmpty())
                throw new EntityNotFoundException("Category with id %s doesnt exists or already deleted".formatted(id));

            return data.get();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public CategoryModel createCategory(CategoryModel categoryModel) {
        try {
            return categoryRepository.save(categoryModel);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public CategoryModel updateCategory(CategoryModel categoryModel) {
        try {
            var data = categoryRepository.findByIdAndDeleted(categoryModel.getId(), false);

            if (data.isEmpty())
                throw new EntityNotFoundException("Category with id %s doesn't exists or already deleted".formatted(categoryModel.getId()));

            var existingData = data.get();

            categoryModel.setCreateBy(existingData.getCreateBy());
            categoryModel.setCreateDate(existingData.getCreateDate());
            categoryModel.setUpdateDate(LocalDateTime.now());

            return categoryRepository.save(categoryModel);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public CategoryModel deleteCategory(CategoryModel model) {
        try {
            var data = categoryRepository.findByIdAndDeleted(model.getId(), false);
//            var count = variantRepository.countVariantModelByCategory_Id(model.getId());

            if (data.isEmpty())
                throw new EntityNotFoundException("Category with id %s doesn't exists or already deleted".formatted(model.getId()));

            var existingData = data.get();

            existingData.setUpdateBy(model.getUpdateBy());
            existingData.setUpdateDate(LocalDateTime.now());
            existingData.setDeleted(true);

            return categoryRepository.save(existingData);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

//    public Optional<List<CategoryModel>> getByNameOrDescription(String filter) {
//        return categoryRepository
//                .findAllByCategoryNameLikeIgnoreCaseOrDescriptionLikeIgnoreCaseAndDeleted(filter, filter, false);
//    }
//
//    public Optional<List<CategoryModel>> getByName(String categoryName) {
//        return categoryRepository.
//                findAllByCategoryNameLikeIgnoreCase(categoryName);
//    }
//
//    public Optional<List<CategoryModel>> getByDesc(String description) {
//        return categoryRepository.
//                findAllByDescriptionLikeIgnoreCase(description);
//    }


}
