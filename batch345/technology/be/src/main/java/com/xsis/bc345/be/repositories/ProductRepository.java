package com.xsis.bc345.be.repositories;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
        """,
        nativeQuery = true
    )
    Optional<List<Map<String, Object>>> findProduct();

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

    @Query(
        nativeQuery = true,
        value = """
            SELECT
                p.id, p.name, p.image, p.price, p.stock,
                p.variant_id AS "variantId", v.name AS "variantName",
                v.category_id AS "categoryId", c.category_name AS "categoryName",
                p.is_deleted AS deleted,
                p.create_by AS "createBy", p.create_date AS "createDate", 
                p.update_by AS "updateBy", p.update_date AS "updateDate"
            FROM
                tbl_m_product AS p
                INNER JOIN tbl_m_variant AS v ON p.variant_id=v.id
                INNER JOIN tbl_m_categories AS c ON v.category_id=c.id
            WHERE
                p.is_deleted = false
        """
    )
    Optional<List<Map<String, Object>>> findAllNative();
    
    @Query(
        nativeQuery = true,
        value = """
            SELECT
                p.id, p.name, p.image, p.price, p.stock,
                p.variant_id AS "variantId", v.name AS "variantName",
                v.category_id AS "categoryId", c.category_name AS "categoryName",
                p.is_deleted AS deleted,
                p.create_by AS "createBy", p.create_date AS "createDate", 
                p.update_by AS "updateBy", p.update_date AS "updateDate"
            FROM
                tbl_m_product AS p
                INNER JOIN tbl_m_variant AS v ON p.variant_id=v.id
                INNER JOIN tbl_m_categories AS c ON v.category_id=c.id
            WHERE
                p.is_deleted = false
        """
    )
    Page<Map<String, Object>> findAllNative(Pageable pageable);

    @Query(
        nativeQuery = true,
        value = """
            SELECT
                p.id, p.name, p.image, p.price, p.stock,
                p.variant_id AS "variantId", v.name AS "variantName",
                v.category_id AS "categoryId", c.category_name AS "categoryName",
                p.is_deleted AS deleted,
                p.create_by AS "createBy", p.create_date AS "createDate", 
                p.update_by AS "updateBy", p.update_date AS "updateDate"
            FROM
                tbl_m_product AS p
                INNER JOIN tbl_m_variant AS v ON p.variant_id=v.id
                INNER JOIN tbl_m_categories AS c ON v.category_id=c.id
            WHERE
                p.is_deleted = false
                AND (
                    LOWER(p.name) LIKE %?1%
                    OR LOWER(v.name) LIKE %?1%
                    OR LOWER(c.category_name) LIKE %?1%
                )
        """
    )
    Optional<List<Map<String, Object>>> findByNative(@Param("filter") String filter);

    @Query(
        nativeQuery = true,
        value = """
            SELECT
                p.id, p.name, p.image, p.price, p.stock,
                p.variant_id AS "variantId", v.name AS "variantName",
                v.category_id AS "categoryId", c.category_name AS "categoryName",
                p.is_deleted AS deleted,
                p.create_by AS "createBy", p.create_date AS "createDate", 
                p.update_by AS "updateBy", p.update_date AS "updateDate"
            FROM
                tbl_m_product AS p
                INNER JOIN tbl_m_variant AS v ON p.variant_id=v.id
                INNER JOIN tbl_m_categories AS c ON v.category_id=c.id
            WHERE
                p.is_deleted = false
                AND (
                    LOWER(p.name) LIKE %?1%
                    OR LOWER(v.name) LIKE %?1%
                    OR LOWER(c.category_name) LIKE %?1%
                )
        """
    )
    Page<Map<String, Object>> findByNative(@Param("filter") String filter, Pageable pageable);
    
}
