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
    private Optional <Category> categoryExisting;

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

    public Optional <Category> getById(int id, boolean deleted){
        return categoryRepo.findByIdAndDeleted(id, deleted);
    }

    public List<Category> getByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(String filter, boolean deleted){
        return categoryRepo.findByCategoryNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(filter, filter, deleted).get();
    }

    public  List<Category> getByNameIgnoreCaseAndDeleted(String categoryName, boolean deleted){
        return categoryRepo.findByCategoryNameIgnoreCaseAndDeleted(categoryName, false).get();
    }

    public  List<Category> getByDescriptionContainsIgnoreCaseAndDeleted(String description, boolean deleted){
        return categoryRepo.findByDescriptionContainsIgnoreCaseAndDeleted(description, false).get();
    }

    public Category create(Category data) throws Exception {
            return categoryRepo.save(data);
            
        
    }

    public Category update(Category data) throws Exception{
       Optional <Category> categoryExisting = categoryRepo.findById(data.getId());
        if (categoryRepo.findById(data.getId()).isPresent()){
            // update fields
            data.setCreateBy(categoryExisting.get().getCreateBy()); 
            data.setCreateDate(categoryExisting.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());

            // update table
            return categoryRepo.save(data);
        } else {

            // TODO Auto-generated method stub
            throw new Exception("Category doesn't exist!");
        }
    }

    public Category delete(int id, int userId) throws Exception{
        categoryExisting = categoryRepo.findById(id);
        
       if(categoryExisting.isPresent()){
        categoryExisting.get().setDeleted(true);
        categoryExisting.get().setUpdateBy(userId);
        categoryExisting.get().setUpdateDate(LocalDateTime.now());
        return categoryRepo.save(categoryExisting.get());
       } else {
        throw new Exception("doesnt exist");
       }
    }
}
