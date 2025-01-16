package com.xsis.bc345.backend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.bc345.backend.models.CategoryModel;
import com.xsis.bc345.backend.repositories.CategoryRepo;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryrepo;
    private Optional<CategoryModel> categoryExist;

    public List<CategoryModel> getALL(){
        try {
            return categoryrepo.findByisDeleted(false).get();
        } catch (Exception e) {
            throw e;
        }
    }

    public Optional<CategoryModel> getbyId(Integer id){
        return categoryrepo.findByIdAndIsDeleted(id,false);
    }

    public List<CategoryModel> getbynameOrdescription(String filter){
        return categoryrepo.findByCategoryNameIgnoreCaseContainingOrDescriptionIgnoreCaseContainingAndIsDeleted(filter,filter,false).get();   
    } 

    public List<CategoryModel> getbyname(String categoryName) {
        return categoryrepo.findByCategoryNameIgnoreCaseContaining(categoryName).get();
    }

    public CategoryModel create(CategoryModel data) throws Exception{
        return categoryrepo.save(data);
    }

    public CategoryModel update(CategoryModel data) throws Exception {
        categoryExist=categoryrepo.findById(data.getId());
        if(categoryExist.isPresent()){
            data.setCreateBy(categoryExist.get().getCreateBy());
            data.setCreateDate(categoryExist.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());
            return categoryrepo.save(data);
        }else{
            throw new Exception("Category Tidak Ada");
        }
    }

    public CategoryModel delete(int id,int userId)throws Exception{
        categoryExist=categoryrepo.findById(id);
        if(categoryExist.isPresent()){
            categoryExist.get().setIsDeleted(true);
            categoryExist.get().setUpdateBy(userId);
            categoryExist.get().setUpdateDate(LocalDateTime.now());
            return categoryrepo.save(categoryExist.get());
        }else{
            throw new Exception("Data Tidak Adak");
        }
    }
}
