package com.xsis.be.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xsis.be.models.Category;
import com.xsis.be.repositories.CategoryRepository;

@Service
public class CategoryService {
    private CategoryRepository categoryRepo;
    private Optional<Category> exsistingCategory;

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
    public Optional<List<Map<String,Object>>> getByNameOrDescription(String filter) throws Exception{
        return categoryRepo.findNameAndCategoryByNativeQuery(filter);
    }

    public Category create(Category data) throws Exception{
        return categoryRepo.save(data);
    }
    public Category update(Category data) throws Exception{
        exsistingCategory = categoryRepo.findById(data.getId());
        if(exsistingCategory.isPresent()){
            // update field
            data.setCreateBy(exsistingCategory.get().getCreateBy());
            data.setCreateDate(exsistingCategory.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());

            // update table
            return categoryRepo.save(data);
        }
        throw new Exception("Category doesn't exsist");
    }
    public Category delete(int id, int userId) throws Exception{
        exsistingCategory = categoryRepo.findById(id);
        if(exsistingCategory.isPresent()){
            exsistingCategory.get().setDeleted(true);
            exsistingCategory.get().setUpdateBy(userId);
            exsistingCategory.get().setUpdateDate(LocalDateTime.now());
            return categoryRepo.save(exsistingCategory.get());
        }
        throw new Exception("Category doesn't exsist");
    }
}
