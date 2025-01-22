package com.xsis.bc345.be.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.xsis.bc345.be.models.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByIdAndDeleted(int id, boolean deleted);
    Optional<List<Category>> findByDeleted(boolean deleted);
    Optional<List<Category>> findByCategoryNameIgnoreCase(String categoryName);
    Optional<List<Category>> findByCategoryNameContainsIgnoreCase(String categoryName);
    Optional<List<Category>> findByCategoryNameIgnoreCaseOrDescriptionIgnoreCase(String categoryName, String description);
    Optional<List<Category>> findByCategoryNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(String categoryName, String description, boolean deleted);


    // @Query(
    //     nativeQuery = true,
    //     value = """
    //             SELECT
    //             FROM tbl_m_product p 
    //                 INNER JOIN tbl_m_variant v ON p.variant_id = v.id
    //                 INNER JOIN tbl_m_categories c ON v.category_id = c.id
    //             WHERE
    //             p.is_deleted IS FALSE AND (
    //                 LOWER(p.name) LIKE %?1%
    //                 or LOWER(v.name) LIKE %?1%
    //                 or LOWER(c.categoryName) LIKE %?1%
    //             )
    //             """
    // )
    // Optional<List<Map<String, Object>>> findByfilter(String filter);

}
