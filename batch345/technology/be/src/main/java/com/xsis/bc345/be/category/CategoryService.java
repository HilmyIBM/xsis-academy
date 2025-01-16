package com.xsis.bc345.be.category;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public List<CategoryModel> getAll() {
        try {
            var data = categoryRepository.findAllByDeleted(false);

            return data.orElse(List.of());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
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

    public CategoryModel updateCategory(CategoryModel categoryModel) {
        var data = categoryRepository.findByIdAndDeleted(categoryModel.getId(), false);

        if (data.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with id %s doesn't exists".formatted(categoryModel.getId()));

        var existingData = data.get();

        categoryModel.setCreateBy(existingData.getCreateBy());
        categoryModel.setCreateDate(existingData.getCreateDate());
        categoryModel.setUpdateDate(LocalDateTime.now());

        return categoryRepository.save(categoryModel);
    }

    public void deleteCategory(CategoryModel model) {
        var data = categoryRepository.findByIdAndDeleted(model.getId(), false);

        if (data.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with id %s doesn't exists".formatted(model.getId()));

        var existingData = data.get();

        existingData.setUpdateBy(model.getUpdateBy());
        existingData.setUpdateDate(LocalDateTime.now());
        existingData.setDeleted(true);

        categoryRepository.save(existingData);
    }


}
