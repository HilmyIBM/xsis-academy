package com.xsis.bc345.be.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Category;
import com.xsis.bc345.be.repositories.CategoryRepository;

import java.lang.StackWalker.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public List<Category> getByName(String name) throws Exception {
        return categoryRepo.findByCategoryNameContainsIgnoreCase(name).get();
    }

    public List<Category> getByNameOrDescription(String filter) throws Exception {
        return categoryRepo
                .findByCategoryNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndDeleted(filter, filter,
                        false)
                .get();
    }

    public Category create(Category data) throws Exception {
        return categoryRepo.save(data);
    }

    public Category update(Category data) throws Exception {
        Optional<Category> categoryExists = categoryRepo.findById(data.getId());
        if(categoryExists.isPresent()) {
            // Update Fields
            data.setCreateBy(categoryExists.get().getCreateBy());
            data.setCreateDate(categoryExists.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());

            // Update Table
            return categoryRepo.save(data);
        }
        throw new Exception("Category doesn't exists");
    }
}
