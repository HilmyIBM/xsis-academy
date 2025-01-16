package com.xsis.bc345.be.repositories;

import java.util.List;
import java.util.Optional;
import com.xsis.bc345.be.models.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositori extends JpaRepository<Category, Integer> {
    // Optional<Category> findById(Integer id);
    Optional<List<Category>> findByDeleted(boolean deleted);
    Optional<Category> findByIdAndDeleted(Integer id, boolean deleted);
    Optional<List<Category>> findByCategoryNameIgnoreCaseAndDeleted(String categoryName, boolean deleted);
    Optional<List<Category>> findByCategoryNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(String categoryName, String description, boolean deleted);
}
