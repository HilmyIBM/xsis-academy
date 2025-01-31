package com.xsis.batch345.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xsis.batch345.backend.model.Category;
import com.xsis.batch345.backend.repository.CategoryRepository;

@Service
public class CategoryService {
  private CategoryRepository categoryRepo;
  private Optional<Category> existingCategory;

  public CategoryService( CategoryRepository categoryRepo ) {
    this.categoryRepo = categoryRepo;
  }


  public Optional<List<Category>> findAll() {
    return categoryRepo.findByDeleted(false);
  }


  public Optional<Category> findById( Integer id ) {
    return categoryRepo.findByIdAndDeleted(id, false);
  }

  public Optional<List<Category>> findByFilter( String filter ) {
    return categoryRepo.findByCategoryNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(filter, filter, false);
  }
  
  public Category create(Category data) throws Exception {
    return categoryRepo.save(data);
  }

  public Category update(Category data) throws Exception {
    Optional<Category> existingCategory = categoryRepo.findById(data.getId());
    if(existingCategory.isPresent()) {
      data.setCreateBy(existingCategory.get().getCreateBy());
      data.setCreateDate(existingCategory.get().getCreateDate());
      data.setUpdateDate(LocalDateTime.now());

      return categoryRepo.save(data);
    } else {
      throw new Exception("Category tidak ada");
    }
  }

  public Category delete(int id, int userId) throws Exception {
    existingCategory = categoryRepo.findById(id);

    if(existingCategory.isPresent()){
      existingCategory.get().setDeleted(true);
      existingCategory.get().setUpdateBy(userId);
      existingCategory.get().setUpdateDate(LocalDateTime.now());

      return categoryRepo.save(existingCategory.get());
    }
    else {
      throw new Exception("Category tidak ditemukan");
    }
  }
}
