package com.xsis.be.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.be.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
    // Optional<Category> findById(Integer id);
    Optional<List<Category>> findByDeleted(boolean deleted);
    Optional<Category> findByIdAndDeleted(Integer id, boolean deleted);
    Optional<List<Category>> findByCategoryNameIgnoreCaseAndDeleted(String categoryName, boolean deleted);
    Optional<List<Category>> findByCategoryNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(String categoryName, String description, boolean deleted);
    
}
