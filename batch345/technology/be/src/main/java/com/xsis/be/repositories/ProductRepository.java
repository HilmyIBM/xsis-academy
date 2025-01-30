package com.xsis.be.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xsis.be.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    Optional<List<Product>>findByDeleted(boolean deleted);

    @Query(value = "SELECT p.id AS \"id\", p.name AS \"name\", price, stock,  c.category_name AS \"categoryName\", variant_id AS \"variantId\", v.name AS \"variantName\", image, "
    + "p.is_deleted AS \"deleted\", p.create_by AS \"createBy\", p.create_date AS \"createDate\", "
    + "p.update_by AS \"updateBy\", p.update_date AS \"updateDate\" "
    + "FROM tbl_m_product AS p INNER JOIN tbl_m_variant AS v ON v.id = p.variant_id "
    + "INNER JOIN tbl_m_categories c ON c.id = v.category_id "
    + "WHERE p.is_deleted IS NOT TRUE AND v.is_deleted IS NOT TRUE AND c.is_deleted IS NOT TRUE",
    nativeQuery = true)
    Optional<List<Map<String,Object>>> findByNativeQuery();

    @Query(value = "SELECT p.id AS \"id\", p.name AS \"name\", price, stock,  c.category_name AS \"categoryName\", variant_id AS \"variantId\", v.name AS \"variantName\", image, "
    + "p.is_deleted AS \"deleted\", p.create_by AS \"createBy\", p.create_date AS \"createDate\", "
    + "p.update_by AS \"updateBy\", p.update_date AS \"updateDate\" "
    + "FROM tbl_m_product AS p INNER JOIN tbl_m_variant AS v ON v.id = p.variant_id "
    + "INNER JOIN tbl_m_categories c ON c.id = v.category_id "
    + "WHERE p.is_deleted IS NOT TRUE AND v.is_deleted IS NOT TRUE AND c.is_deleted IS NOT TRUE",
    nativeQuery = true)
    Page<Map<String,Object>> findByNativeQuery(Pageable pageable);




    @Query(value = "SELECT p.id AS \"id\", p.name AS \"name\", price, stock,  c.category_name AS \"categoryName\", variant_id AS \"variantId\", v.name AS \"variantName\", image, "
    + "p.is_deleted AS \"deleted\", p.create_by AS \"createBy\", p.create_date AS \"createDate\", "
    + "p.update_by AS \"updateBy\", p.update_date AS \"updateDate\" "
    + "FROM tbl_m_product AS p INNER JOIN tbl_m_variant AS v ON v.id = p.variant_id "
    + "INNER JOIN tbl_m_categories c ON c.id = v.category_id "
    + "WHERE (p.is_deleted IS NOT TRUE AND v.is_deleted IS NOT TRUE AND c.is_deleted IS NOT TRUE AND :inputId = p.id)",
    nativeQuery = true)
    Optional<Map<String,Object>> findIdByNativeQuery(@Param("inputId") int inputId);

    @Query(value = "SELECT p.id AS \"id\", p.name AS \"name\", price, stock,  c.category_name AS \"categoryName\", variant_id AS \"variantId\", v.name AS \"variantName\", image, "
    + "p.is_deleted AS \"deleted\", p.create_by AS \"createBy\", p.create_date AS \"createDate\", "
    + "p.update_by AS \"updateBy\", p.update_date AS \"updateDate\" "
    + "FROM tbl_m_product AS p INNER JOIN tbl_m_variant AS v ON v.id = p.variant_id "
    + "INNER JOIN tbl_m_categories c ON c.id = v.category_id "
    + "WHERE p.is_deleted IS NOT TRUE AND v.is_deleted IS NOT TRUE AND c.is_deleted IS NOT TRUE "
    + "AND ((LOWER(v.name) LIKE LOWER(CONCAT('%', :filter, '%')) OR LOWER(p.name) LIKE LOWER(CONCAT('%', :filter, '%'))))",
    nativeQuery = true)
    Optional<List<Map<String,Object>>> findByNativeQueryFilter(@Param("filter") String filter);

    @Query(value = "SELECT p.id AS \"id\", p.name AS \"name\", price, stock,  c.category_name AS \"categoryName\", variant_id AS \"variantId\", v.name AS \"variantName\", image, "
    + "p.is_deleted AS \"deleted\", p.create_by AS \"createBy\", p.create_date AS \"createDate\", "
    + "p.update_by AS \"updateBy\", p.update_date AS \"updateDate\" "
    + "FROM tbl_m_product AS p INNER JOIN tbl_m_variant AS v ON v.id = p.variant_id "
    + "INNER JOIN tbl_m_categories c ON c.id = v.category_id "
    + "WHERE p.is_deleted IS NOT TRUE AND v.is_deleted IS NOT TRUE AND c.is_deleted IS NOT TRUE "
    + "AND ((LOWER(v.name) LIKE LOWER(CONCAT('%', :filter, '%')) OR LOWER(p.name) LIKE LOWER(CONCAT('%', :filter, '%'))))",
    nativeQuery = true)
    Page<Map<String,Object>> findByNativeQueryFilter(@Param("filter") String filter, Pageable pageable);
}
