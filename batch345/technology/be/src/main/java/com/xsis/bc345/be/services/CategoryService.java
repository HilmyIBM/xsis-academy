package com.xsis.bc345.be.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Category;
import com.xsis.bc345.be.repositories.CategoryRepository;

@Service
public class CategoryService {
    private CategoryRepository categoryRepo;
    private Optional<Category> existingCategory;
    
    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> getAll() {
        try {
            return categoryRepo.findByDeleted(false).get();
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }

    public Optional<Category> getBy(int id) throws Exception {
        return categoryRepo.findByIdAndDeleted(id, false);
    }

    public List<Category> getBy(String filter) throws Exception {
        return categoryRepo.findByCategoryNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(filter, filter, false).get();
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
            //Update Fields
            data.setCreateBy(existingCategory.get().getCreateBy());
            data.setCreateDate(existingCategory.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());

            //Update Table
            return categoryRepo.save(data);
        }
        else {
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
        }
        else {
            throw new Exception("Category doesn't exist!");
        }
    }
}
