package com.xsis.backend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xsis.backend.models.Category;
import com.xsis.backend.repositories.CategoryRepository;

@Service
public class CategoryService {
    private CategoryRepository categoryRepo;

    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> getAll() throws Exception {
        return categoryRepo.findByDeleted(false).get();
    }

    public Optional<Category> getById(int id) throws Exception {
        return categoryRepo.findByIdAndDeleted(id, false);
    }

    public List<Category> getByNameOrDescription(String filter) throws Exception {
        return categoryRepo
                .findByCategoryNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(filter, filter,
                        false)
                .get();

    }

    public List<Category> getByName(String categoryName) throws Exception {
        return categoryRepo.findByCategoryNameContainsIgnoreCase(categoryName).get();

    }

    public Category create(Category data) throws Exception {
        return categoryRepo.save(data);
    }

    public Category update(Category data) throws Exception {
        Optional<Category> categoryExisting = categoryRepo.findById(data.getId());
        if (categoryExisting.isPresent()) {
            // Update Fields
            data.setCreateBy(categoryExisting.get().getCreateBy());
            data.setCreateDate(categoryExisting.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());

            // Update Table
            return categoryRepo.save(data);
        } else {
            throw new Exception("Category doesn't exist!");
        }
    }
}