package com.xsis.bc345.be.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.be.models.Product;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByIdAndDeleted(int id, boolean deleted);
    Optional<List<Product>> findByDeleted(boolean deleted);
    Optional<List<Product>> findByNameIgnoreCase(String variantName);
    Optional<List<Product>> findByNameContainsIgnoreCase(String variantName);
    Optional<List<Product>> findByNameContainsIgnoreCaseAndDeleted(String variantName, boolean deleted);

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
                v.category_id AS "categoryId", c.categoruy_name AS "categoryName",
                p.is_deleted AS deleted,
                p.create_by AS "createBy", p.create_date AS "createDate", 
                p.update_by AS "updateBy", p.update_date AS "updateDate"
            FROM
                tbl_m_product AS p
                INNER JOIN tbl_m_variant AS v ON p.variant_id=v.id
                INNER JOIN tbl_m_categories AS c ON v.category_id=c.id
            WHERE
                p.is_deleted = false AND p.id = :id
        """
    )
    Optional<Map<String, Object>> findByNative(@Param("id") int id);

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
    Optional<Map<String, Object>> findByNative(@Param("filter") String filter);
}
