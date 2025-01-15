package com.xsis.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.backend.models.Category;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByIdAndDeleted(int id, boolean deleted);

    Optional<List<Category>> findByCategoryNameIgnoreCaseOrDescriptionIgnoreCaseAndDeleted(String categoryName,
            String description, boolean deleted);

    Optional<List<Category>> findByCategoryNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(
            String categoryName,
            String description, boolean deleted);

    Optional<List<Category>> findByDeleted(boolean deleted);

    Optional<List<Category>> findByCategoryNameContainsIgnoreCase(String categoryName);
}
