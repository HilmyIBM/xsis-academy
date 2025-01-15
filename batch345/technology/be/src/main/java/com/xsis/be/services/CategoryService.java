package com.xsis.be.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xsis.be.models.Category;
import com.xsis.be.repositories.CategoryRepository;

@Service
public class CategoryService {
    private CategoryRepository categoryRepo;

    public CategoryService(CategoryRepository categoryRepo){
        this.categoryRepo = categoryRepo;

    }
    public List<Category> getAll() throws Exception{
        return categoryRepo.findByDeleted(false).get();
    }

    public Optional<Category> getById(Integer id) throws Exception{
        return categoryRepo.findByIdAndDeleted(id, false);
    }

    public Optional<List<Category>> getByCategoryName(String categoryName) throws Exception{
        return categoryRepo.findByCategoryNameIgnoreCaseAndDeleted(categoryName, false);
    }
    public Optional<List<Category>> getByNameOrDescription(String filter) throws Exception{
        return categoryRepo.findByCategoryNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(filter, filter, false);
    }

    public Category create(Category data) throws Exception{
        return categoryRepo.save(data);
    }
    public Category update(Category data) throws Exception{
        Optional<Category> catergoryExsist = categoryRepo.findById(data.getId());
        if(catergoryExsist.isPresent()){
            // update field
            data.setCreateBy(catergoryExsist.get().getCreateBy());
            data.setCreateDate(catergoryExsist.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());

            // update table
            return categoryRepo.save(data);
        }
        throw new Exception("Category doesn't exsist");
    }
    public Category delete(Category data) throws Exception{
        Optional<Category> catergoryExsist = categoryRepo.findById(data.getId());
        if(catergoryExsist.isPresent()){
            categoryRepo.deleteById(data.getId());
        }
        throw new Exception("Category doesn't exsist");
    }
}
