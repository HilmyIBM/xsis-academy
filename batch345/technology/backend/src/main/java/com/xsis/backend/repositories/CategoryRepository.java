package com.xsis.backend.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xsis.backend.models.Category;
import java.util.List;
import java.util.Map;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
        Optional<Category> findByIdAndDeleted(int id, boolean deleted);

        Optional<List<Category>> findByCategoryNameIgnoreCaseOrDescriptionIgnoreCaseAndDeleted(String categoryName,
                        String description, boolean deleted);

        Optional<List<Category>> findByDeletedFalseAndCategoryNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(
                        String categoryName, String description);

        Optional<List<Category>> findByDeleted(boolean deleted);

        Optional<List<Category>> findByCategoryNameContainsIgnoreCase(String categoryName);

        @Query(value = """
                        SELECT
                        	c.id, c.category_name AS "categoryName", c.description,
                        	c.is_deleted AS "deleted", c.create_by AS "createBy",
                        	c.create_date AS "createDate", c.update_by AS "updateBy",
                        	c.update_date AS "updateDate"
                        FROM tbl_m_categories AS c
                        WHERE c.is_deleted = FALSE
                        """, nativeQuery = true)
        Page<Map<String, Object>> findAllNative(Pageable pageable);

        @Query(value = "SELECT c.id, c.category_name, c.description, c.is_deleted, "
                        + "c.create_by, c.create_date, c.update_by, "
                        + "c.update_date FROM tbl_m_categories c WHERE c.is_deleted = FALSE AND "
                        + "(LOWER(c.category_name) LIKE LOWER(CONCAT('%', :filter, '%')) OR "
                        + "LOWER(c.description) LIKE LOWER(CONCAT('%', :filter, '%')))", nativeQuery = true)
        Optional<List<Category>> findByFilter(@Param("filter") String filter);

        @Query(value = """
                        SELECT c.id, c.category_name, c.description, c.is_deleted,
                            c.create_by, c.create_date, c.update_by,
                            c.update_date
                        FROM tbl_m_categories c
                        WHERE c.is_deleted = FALSE AND (LOWER(c.category_name) LIKE LOWER(CONCAT('%', :filter, '%')) OR
                            LOWER(c.description) LIKE LOWER(CONCAT('%', :filter, '%')))
                            """, nativeQuery = true)
        Page<Category> findByFilter(@Param("filter") String filter, Pageable pageable);
}
