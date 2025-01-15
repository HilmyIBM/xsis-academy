package com.xsis.bc345.be.category;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<List<CategoryModel>> getAll() {
        return categoryRepository.findAllByDeleted(false);
    }

    public Optional<CategoryModel> getById(int id) {
        return categoryRepository
                .findByIdAndDeleted(id, false);
    }

    public Optional<List<CategoryModel>> getByNameOrDescription(String filter) {
        return categoryRepository
                .findAllByCategoryNameLikeIgnoreCaseOrDescriptionLikeIgnoreCaseAndDeleted(filter, filter, false);
    }

    public Optional<List<CategoryModel>> getByName(String categoryName) {
        return categoryRepository.
                findAllByCategoryNameLikeIgnoreCase(categoryName);
    }

    public Optional<List<CategoryModel>> getByDesc(String description) {
        return categoryRepository.
                findAllByDescriptionLikeIgnoreCase(description);
    }

    public Optional<CategoryModel> createCategory(CategoryModel categoryModel) {
        return Optional.of(categoryRepository.save(categoryModel));
    }

    public Optional<CategoryModel> updateCategory(CategoryModel categoryModel) {
        Optional<CategoryModel> category = categoryRepository.findByIdAndDeleted(categoryModel.getId(), false);

        if (category.isEmpty())
            throw new EntityNotFoundException("Category with id " + categoryModel.getId() + " doesnt exists");

        categoryModel.setCreateBy(category.get().getCreateBy());
        categoryModel.setCreateDate(category.get().getCreateDate());
        categoryModel.setUpdateDate(LocalDateTime.now());

        return Optional.of(categoryRepository.save(categoryModel));
    }
}
