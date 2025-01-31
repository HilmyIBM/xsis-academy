package com.xsis.batch345.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xsis.batch345.backend.model.Category;
import com.xsis.batch345.backend.repository.CategoryRepository;

@Service
public class CategoryService {
  private final CategoryRepository categoryRepo;
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

}
