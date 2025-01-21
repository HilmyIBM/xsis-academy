package com.xsis.be.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xsis.be.models.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Integer> {
    Optional<List<Variant>> findByDeleted(boolean deleted);
    Optional<Variant> findByIdAndDeleted(Integer id, boolean deleted);
    Optional<List<Variant>> findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(String name, String description, boolean deleted);


    @Query(value = "SELECT v.id, v.category_id AS \"categoryId\", v.name, v.description, c.category_name AS \"categoryName\", "
    + "v.is_deleted AS \"deleted\", v.create_by AS \"createBy\", v.create_date AS \"createDate\", "
    + "v.update_by AS \"updateBy\", v.update_date AS \"updateDate\" "
    + "FROM tbl_m_variant AS v INNER JOIN tbl_m_categories AS c ON v.category_id = c.id "
    + "WHERE v.is_deleted = FALSE AND "
    + "(LOWER(c.category_name) LIKE LOWER(CONCAT('%', :filter, '%')) OR LOWER(v.description) LIKE LOWER(CONCAT('%', :filter, '%')) OR LOWER(v.name) LIKE LOWER(CONCAT('%', :filter, '%')))", 
    nativeQuery =true)
    Optional<List<Map<String,Object>>> findNameAndDescVariantByNativeQuery(@Param("filter") String filter);

    @Query(value = "SELECT v.id, v.category_id AS \"categoryId\", v.name, v.description, c.category_name AS \"categoryName\", "
    + "v.is_deleted AS \"deleted\", v.create_by AS \"createBy\", v.create_date AS \"createDate\", "
    + "v.update_by AS \"updateBy\", v.update_date AS \"updateDate\" "
    + "FROM tbl_m_variant AS v INNER JOIN tbl_m_categories AS c ON v.category_id = c.id "
    + "WHERE v.is_deleted IS NOT TRUE",
    nativeQuery = true)
    Optional<List<Map<String,Object>>> findByNativeQuery();
    // String findCategoryNameByVariantId(@Param("variantId") Integer variantId);

}