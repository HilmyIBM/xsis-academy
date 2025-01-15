package com.xsis.bc345.be.repositories;

@Repository
public class CategoryRepositori {
    // Optional<Category> findById(Integer id);
    Optional<List<Category>> findByDeleted(boolean deleted);
    Optional<Category> findByIdAndDeleted(Integer id, boolean deleted);
    Optional<List<Category>> findByCategoryNameIgnoreCaseAndDeleted(String categoryName, boolean deleted);
    Optional<List<Category>> findByCategoryNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(String categoryName, String description, boolean deleted);
}
