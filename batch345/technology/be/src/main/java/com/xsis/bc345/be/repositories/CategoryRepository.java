package com.xsis.bc345.be.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.be.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByIdAndDeleted(int id, boolean deleted);
    Optional<List<Category>> findByDeleted(boolean deleted);
    Optional<List<Category>> findByCategoryNameIgnoreCase(String categoryName);
    // Optional<List<Category>> findByCategoryNameContainsIgnoreCase(String categoryName);
    Optional<List<Category>> findByCategoryNameIgnoreCaseOrDescriptionIgnoreCaseAndDeleted(String categoryName, String description, boolean deleted);
    Optional<List<Category>> findByCategoryNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(String categoryName, String description, boolean deleted);
}
