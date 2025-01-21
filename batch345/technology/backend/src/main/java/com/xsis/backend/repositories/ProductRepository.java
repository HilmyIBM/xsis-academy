package com.xsis.backend.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xsis.backend.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<List<Product>> findByDeleted(boolean deleted);

    Optional<Product> findByIdAndDeleted(int id, boolean deleted);

    @Query(value = "SELECT p.id, p.name, c.category_name AS \"categoryName\", v.name AS \"variantName\", "
            + "p.price, p.stock, p.variant_id AS \"variantId\", p.image, p.is_deleted AS \"deleted\", "
            + "p.create_by AS \"createBy\", p.create_date AS \"createDate\", p.update_by AS \"updateBy\", "
            + "p.update_date AS \"updateDate\" "
            + "FROM tbl_m_product p "
            + "INNER JOIN tbl_m_variant v ON p.variant_id = v.id "
            + "INNER JOIN tbl_m_categories c ON v.category_id = c.id "
            + "WHERE p.id = :id AND p.is_deleted = FALSE", nativeQuery = true)
    Optional<Map<String, Object>> findByIdCustom(@Param("id") int id);

    @Query(value = "SELECT p.id, p.name, c.category_name AS \"categoryName\", v.name AS \"variantName\", p.price, p.stock, p.variant_id AS \"variantId\", "
            + "p.image, p.is_deleted AS \"deleted\", p.create_by AS \"createBy\", p.create_date AS \"createDate\", "
            + "p.update_by AS \"updateBy\", p.update_date AS \"updateDate\" "
            + "FROM tbl_m_product AS p INNER JOIN tbl_m_variant AS v ON p.variant_id = v.id "
            + "INNER JOIN tbl_m_categories AS c ON v.category_id = c.id "
            + "WHERE p.is_deleted = FALSE", nativeQuery = true)
    Optional<List<Map<String, Object>>> findAllNative();

    @Query(value = "SELECT p.id, p.name, c.category_name AS \"categoryName\", v.name AS \"variantName\", p.price, p.stock, p.variant_id AS \"variantId\", "
            + "p.image, p.is_deleted AS \"deleted\", p.create_by AS \"createBy\", p.create_date AS \"createDate\", "
            + "p.update_by AS \"updateBy\", p.update_date AS \"updateDate\" "
            + "FROM tbl_m_product AS p INNER JOIN tbl_m_variant AS v ON p.variant_id = v.id "
            + "INNER JOIN tbl_m_categories AS c ON v.category_id = c.id "
            + "WHERE p.is_deleted = FALSE AND (LOWER(p.name) LIKE LOWER(CONCAT('%', :filter, '%')) OR "
            + "LOWER(c.category_name) LIKE LOWER(CONCAT('%', :filter, '%')) OR "
            + "LOWER(v.name) LIKE LOWER(CONCAT('%', :filter, '%')))", nativeQuery = true)
    Optional<List<Map<String, Object>>> findByFilter(@Param("filter") String filter);
}
