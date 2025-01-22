package com.xsis.bc345.be.category;

import com.xsis.bc345.be.variant.VariantRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final VariantRepository variantRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, VariantRepository variantRepository) {
        this.categoryRepository = categoryRepository;
        this.variantRepository = variantRepository;
    }

    public List<CategoryModel> getAll() {
        return categoryRepository.findAllByDeleted(false).orElse(List.of());
    }

    public List<CategoryModel> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CategoryModel> pageCategory = categoryRepository.findAllByDeleted(false, pageable);

        return pageCategory.getContent();
    }

    public CategoryModel getById(int id, boolean deleted) {
        var data = categoryRepository
                .findByIdAndDeleted(id, deleted);

        if (data.isEmpty())
            throw new EntityNotFoundException("Category with id %s doesnt exists or already deleted".formatted(id));

        return data.get();
    }

    public CategoryModel createCategory(CategoryModel categoryModel) {
        return categoryRepository.save(categoryModel);
    }

    public CategoryModel updateCategory(CategoryModel categoryModel) {
        var category = categoryRepository.findByIdAndDeleted(categoryModel.getId(), false);

        if (category.isEmpty())
            throw new EntityNotFoundException("Category with id %s doesn't exists or already deleted".formatted(categoryModel.getId()));

        var existingCategory = category.get();

        categoryModel.setCreateBy(existingCategory.getCreateBy());
        categoryModel.setCreateDate(existingCategory.getCreateDate());
        categoryModel.setUpdateDate(LocalDateTime.now());

        return categoryRepository.save(categoryModel);
    }

    public CategoryModel deleteCategory(CategoryModel model) {
        var category = categoryRepository.findByIdAndDeleted(model.getId(), false);
        var relatedVariants = variantRepository.countAllByCategory_DeletedAndId(false, model.getId());

        if (category.isEmpty())
            throw new EntityNotFoundException("Category with id %s doesn't exists or already deleted".formatted(model.getId()));

        if (relatedVariants > 0)
            throw new EntityExistsException("Cannot delete categories with id %s because they are used by variants".formatted(model.getId()));

        var existingCategory = category.get();

        existingCategory.setUpdateBy(model.getUpdateBy());
        existingCategory.setUpdateDate(LocalDateTime.now());
        existingCategory.setDeleted(true);

        return categoryRepository.save(existingCategory);
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
