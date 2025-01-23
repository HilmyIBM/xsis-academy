package com.xsis.bc345.be.repositories;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.be.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<List<Product>> findByDeleted(boolean deleted);

    @Query(value = 
        """
            SELECT p.*, v.name AS "variantName", c.category_name AS "categoryName"
            FROM tbl_m_product p 
                LEFT JOIN tbl_m_variant v ON p.variant_id = v.id
                LEFT JOIN tbl_m_categories c ON v.category_id = c.id
            WHERE p.is_deleted IS FALSE
                AND v.is_deleted IS FALSE
                AND c.is_deleted IS FALSE
            ORDER BY p.id;
        """,
        nativeQuery = true
    )
    Optional<List<Map<String, Object>>> findProduct();

    @Query(
        nativeQuery = true,
        value = 
        """
            SELECT p.*, v.id AS "variantId", v.name AS "variantName", c.id AS "categoryId", c.category_name AS "categoryName"
            FROM tbl_m_product p
                INNER JOIN tbl_m_variant v ON p.variant_id = v.id
                INNER JOIN tbl_m_categories c ON v.category_id = c.id
            WHERE p.is_deleted IS FALSE AND (
                LOWER(p.name) LIKE %?1%
                OR LOWER(v.name) LIKE %?1%
                OR LOWER(c.category_name) LIKE %?1%
            )
        """
    )
    Optional<List<Map<String, Object>>> findByFilter(String filter);

    @Query(
        nativeQuery = true,
        value =
        """
            SELECT p.*, p.create_date AS "createDate", p.update_date AS "updateDate", p.update_by AS "updateBy", v.id AS "variantId", v.name AS "variantName", c.id AS "categoryId", c.category_name AS "categoryName"
            FROM tbl_m_product p
                INNER JOIN tbl_m_variant v ON p.variant_id = v.id
                INNER JOIN tbl_m_categories c ON v.category_id = c.id  
            WHERE p.id = :id 
        """
    )
    Optional<Map<String, Object>> findByIdNative(int id);
    
}
