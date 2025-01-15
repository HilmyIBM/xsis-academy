package com.xsis.bc345.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.backend.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
  Optional<List<Category>> findByDeleted(boolean deleted); 
  Optional<Category> findByIdAndDeleted(int id, boolean deleted);
  Optional<List<Category>> findByCategoryNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(String categoryName, String description, boolean deleted);
  Optional<List<Category>> findByCategoryNameContainsIgnoreCaseAndDeleted(String categoryName, boolean deleted);
}
