package com.xsis.backend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xsis.backend.models.Category;
import com.xsis.backend.repositories.CategoryRepository;

@Service
public class CategoryService {
    private CategoryRepository categoryRepo;
    private Optional<Category> existingCategory;

    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> getAll() throws Exception {
        return categoryRepo.findByDeleted(false).get();
    }

    public Page<Map<String, Object>> getAll(Pageable pageable) {
        return categoryRepo.findAllNative(pageable);
    }

    public Optional<Category> getById(int id) throws Exception {
        return categoryRepo.findByIdAndDeleted(id, false);
    }

    public List<Category> getByNameOrDescription(String filter) throws Exception {
        return categoryRepo
                .getNativeByDeletedFalseAndCategoryNameOrDescription(filter)
                .get();
    }

    public Page<Map<String, Object>> getByNameOrDescription(String filter, Pageable pageable) throws Exception {
        return categoryRepo
                .getNativeByDeletedFalseAndCategoryNameOrDescription(filter, pageable);
    }

    public List<Category> getByName(String categoryName) throws Exception {
        return categoryRepo.findByCategoryNameContainsIgnoreCase(categoryName).get();

    }

    public Category create(Category data) throws Exception {
        return categoryRepo.save(data);
    }

    public Category update(Category data) throws Exception {
        existingCategory = categoryRepo.findById(data.getId());
        if (existingCategory.isPresent()) {
            // Update Fields
            data.setCreateBy(existingCategory.get().getCreateBy());
            data.setCreateDate(existingCategory.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());

            // Update Table
            return categoryRepo.save(data);
        } else {
            throw new Exception("Category doesn't exist!");
        }
    }

    public Category delete(int id, int userId) throws Exception {
        existingCategory = categoryRepo.findById(id);
        if (existingCategory.isPresent()) {
            existingCategory.get().setDeleted(true);
            existingCategory.get().setUpdateBy(userId);
            existingCategory.get().setUpdateDate(LocalDateTime.now());
            return categoryRepo.save(existingCategory.get());
        } else {
            throw new Exception("Category doesn't exist!");
        }
    }
}