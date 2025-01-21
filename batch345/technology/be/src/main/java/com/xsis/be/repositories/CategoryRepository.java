package com.xsis.be.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xsis.be.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
    // Optional<Category> findById(Integer id);
    Optional<List<Category>> findByDeleted(boolean deleted);
    Optional<Category> findByIdAndDeleted(Integer id, boolean deleted);
    Optional<List<Category>> findByCategoryNameIgnoreCaseAndDeleted(String categoryName, boolean deleted);
    Optional<List<Category>> findByCategoryNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(String categoryName, String description, boolean deleted);

    // @Query(value = "SELECT v.id, v.category_id AS \"categoryId\", v.name, v.description, c.category_name AS \"categoryName\", "
    // + "v.is_deleted AS \"deleted\", v.create_by AS \"createBy\", v.create_date AS \"createDate\", "
    // + "v.update_by AS \"updateBy\", v.update_date AS \"updateDate\" "
    // + "FROM tbl_m_variant AS v INNER JOIN tbl_m_categories AS c ON v.category_id = c.id "
    // + "WHERE v.is_deleted IS NOT TRUE",
    // nativeQuery = true)
    //  Optional<List<Map<String,Object>>> findByNativeQuery();

     @Query(value = "SELECT id, category_name AS \"categoryName\", description, is_deleted AS deleted, create_by AS \"createBy\", create_date AS \"createDate\", update_by AS \"updateBy\", update_date AS \"updateDate\" " 
     + "FROM tbl_m_categories AS C "
     + "WHERE is_deleted = FALSE AND (LOWER(category_name) LIKE LOWER(CONCAT('%', :filter, '%')) OR LOWER(description) LIKE LOWER(CONCAT('%', :filter, '%')))",
     nativeQuery = true)
     Optional<List<Map<String,Object>>> findNameAndCategoryByNativeQuery(@Param("filter") String filter);
}
