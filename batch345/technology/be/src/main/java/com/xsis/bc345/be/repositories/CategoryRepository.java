package com.xsis.bc345.be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.be.models.Category;
import java.util.List;
import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {


    Optional<Category> findByIdAndDeleted(int id, boolean deleted);
    Optional<List<Category>> findByDeleted(boolean deleted);
    Optional<List<Category>> findByCategoryName(String categoryName);
    Optional<List<Category>> findByCategoryNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(String name, String description, boolean deleted);
    Optional<List<Category>> findByCategoryNameIgnoreCaseAndDeleted(String name, boolean deleted);
    Optional<List<Category>> findByDescriptionContainsIgnoreCaseAndDeleted(String description, boolean deleted);
    
}