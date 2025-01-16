package com.xsis.bc345.backend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xsis.bc345.backend.models.Category;
import com.xsis.bc345.backend.repositories.CategoryRepository;

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
      throw e;
    }
  }

  public Optional<Category> getById(int id) throws Exception{
    return categoryRepo.findByIdAndDeleted(id, false);
  }

  public List<Category> getByFilter(String filter) throws Exception{
    return categoryRepo.findByCategoryNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(filter, filter, false).get();
  }

  public List<Category> getByName(String categoryName) throws Exception {
    return categoryRepo.findByCategoryNameContainsIgnoreCaseAndDeleted(categoryName, false).get();
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
