package com.xsis.bc345.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.backend.models.CategoryModel;


@Repository
public interface CategoryRepo extends JpaRepository<CategoryModel, Integer>{
    Optional<List<CategoryModel>> findByisDeleted(boolean isDeleted);
    Optional<CategoryModel>findByIdAndIsDeleted(Integer id,boolean isDeleted);
    Optional<List<CategoryModel>>findByCategoryNameIgnoreCaseContainingOrDescriptionIgnoreCaseContainingAndIsDeleted(String categoryName, String description,boolean isDeleted);
    Optional<List<CategoryModel>>findByCategoryNameIgnoreCaseContaining(String categoryName);

    Page<CategoryModel> findByIsDeleted(boolean isDeleted, Pageable pageable);
    Page<CategoryModel> findByCategoryNameIgnoreCaseContainingOrDescriptionIgnoreCaseContainingAndIsDeleted(String categoryName, String description, boolean isDeleted, Pageable pageable);
    
} 