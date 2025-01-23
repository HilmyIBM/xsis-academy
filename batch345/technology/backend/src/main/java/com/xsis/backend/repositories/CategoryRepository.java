package com.xsis.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xsis.backend.models.Category;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
        Optional<Category> findByIdAndDeleted(int id, boolean deleted);

        Optional<List<Category>> findByCategoryNameIgnoreCaseOrDescriptionIgnoreCaseAndDeleted(String categoryName,
                        String description, boolean deleted);

        Optional<List<Category>> findByDeletedFalseAndCategoryNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(
                        String categoryName, String description);

        Optional<List<Category>> findByDeleted(boolean deleted);

        Optional<List<Category>> findByCategoryNameContainsIgnoreCase(String categoryName);

        @Query(value = "SELECT c.id, c.category_name, c.description, c.is_deleted, "
                        + "c.create_by, c.create_date, c.update_by, "
                        + "c.update_date FROM tbl_m_categories c WHERE c.is_deleted = FALSE AND "
                        + "(LOWER(c.category_name) LIKE LOWER(CONCAT('%', :filter, '%')) OR "
                        + "LOWER(c.description) LIKE LOWER(CONCAT('%', :filter, '%')))", nativeQuery = true)
        Optional<List<Category>> getNativeByDeletedFalseAndCategoryNameOrDescription(@Param("filter") String filter);   
}
