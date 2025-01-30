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
    private CategoryRepository categoryRepository;
    private Optional<Category> existingCategory;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() throws Exception {
        return categoryRepository.findByDeleted(false).get();
    }

    public Page<Map<String, Object>> getAll(Pageable pageable) throws Exception {
        return categoryRepository.findAllNative(pageable);
    }

    public Optional<Category> getById(int id) throws Exception {
        return categoryRepository.findByIdAndDeleted(id, false);
    }

    public List<Category> getByFilter(String filter) throws Exception {
        return categoryRepository.findByFilter(filter).get();
    }

    public Page<Category> getByFilter(String filter, Pageable pageable) throws Exception {
        return categoryRepository.findByFilter(filter, pageable);
    }

    public List<Category> getByName(String categoryName) throws Exception {
        return categoryRepository.findByCategoryNameContainsIgnoreCase(categoryName).get();

    }

    public Category create(Category data) throws Exception {
        return categoryRepository.save(data);
    }

    public Category update(Category data) throws Exception {
        existingCategory = categoryRepository.findById(data.getId());
        if (existingCategory.isPresent()) {
            // Update Fields
            data.setCreateBy(existingCategory.get().getCreateBy());
            data.setCreateDate(existingCategory.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());

            // Update Table
            return categoryRepository.save(data);
        } else {
            throw new Exception("Category doesn't exist!");
        }
    }

    public Category delete(int id, int userId) throws Exception {
        existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            existingCategory.get().setDeleted(true);
            existingCategory.get().setUpdateBy(userId);
            existingCategory.get().setUpdateDate(LocalDateTime.now());
            return categoryRepository.save(existingCategory.get());
        } else {
            throw new Exception("Category doesn't exist!");
        }
    }
}