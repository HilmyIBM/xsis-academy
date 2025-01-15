package com.xsis.bc345.be.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Category;
import com.xsis.bc345.be.repositories.CategoryRepository;

@Service
public class CategoryService {
    private CategoryRepository categoryRepo;

    public CategoryService(CategoryRepository categoryRepo){
        this.categoryRepo = categoryRepo;
    }

    public List<Category> getAll(){
        try {
            return categoryRepo.findByDeleted(false).get();
        } catch (Exception e) {
            throw e;
        }
    }

    public Optional<Category> getById(int id){
        return categoryRepo.findByIdAndDeleted(id, false);
    }

    public List<Category> getByNameorDescription(String filter){
        // return categoryRepo.findByCategoryNameIgnoreCaseOrDescriptionIgnoreCaseAndDeleted(filter, filter, false).get();
        return categoryRepo.findByCategoryNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(filter, filter, false).get();
    }

    public List<Category> getByName(String categoryName) {
        return categoryRepo.findByCategoryNameIgnoreCase(categoryName).get();
        // return categoryRepo.findByCategoryNameContainsIgnoreCase(categoryName).get();
    }

    public Category create(Category data) throws Exception{
        return categoryRepo.save(data);
    }

    public Category update(Category data) throws Exception {
        Optional <Category> categoryExisting = categoryRepo.findById(data.getId());
        if(categoryExisting.isPresent()){
            data.setCreateBy(categoryExisting.get().getCreateBy());
            data.setCreateDate(categoryExisting.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());

            // Update
            return categoryRepo.save(data);
        }else{
            throw new Exception("Category doesn't exist!");
        }
    }
}
