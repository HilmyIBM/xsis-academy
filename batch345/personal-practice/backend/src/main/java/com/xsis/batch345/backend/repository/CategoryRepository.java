package com.xsis.batch345.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.batch345.backend.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
  Optional<List<Category>> findByDeleted(boolean deleted);
  Optional<Category> findByIdAndDeleted(Integer id, boolean deleted);
  Optional<List<Category>> findByCategoryNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(String categoryName, String description, boolean deleted);
}
