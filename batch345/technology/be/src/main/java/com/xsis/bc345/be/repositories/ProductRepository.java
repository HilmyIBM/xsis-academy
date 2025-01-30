package com.xsis.bc345.be.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xsis.bc345.be.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
        Optional<List<Product>> findByDeleted(boolean deleted);

        Optional<List<Product>> findByCategoryContainsIgnoreCaseOrNameContainsIgnoreCaseAndDeleted(String Category,
                        String Name,
                        boolean deleted);

        // Pagination tidak memakai list, langsung menjadi Page
        @Query(value = """
                        Select p.id,p.price,p.stock,p.image,
                                v.id as "variantId",
                                v.name AS "variantName",
                                c.id as "categoryId",
                                p.name as "name",
                                c.category_name as "categoryName",
                                p.is_deleted as "deleted",
                                p.create_by as "createBy",
                                p.create_date as "createDate",
                                p.update_by as "updateBy",
                                p.update_date as "updateDate",
                                v.name as "variantName",
                                c.category_name as "categoryName"
                        from tbl_m_product p
                                inner join tbl_m_variant v on v.id = p.variant_id
                                inner join tbl_m_categories c on c.id = v.category_id
                        where p.is_deleted is not true
                        """, nativeQuery = true)
        Page<Map<String, Object>> findAllNativePage(Pageable page);

        @Query(value = """
                        SELECT a.*, b.name as "variantName", c.category_name as "categoryName" from tbl_m_product a
                        inner join tbl_m_variant b on b.id = a.variant_id
                        inner join tbl_m_categories c on c.id = b.category_id
                        where a.is_deleted is not true
                        """, nativeQuery = true)
        Optional<List<Map<String, Object>>> findAllNative();

        @Query(value = """
                        SELECT p.id, p.name, p.price, p.stock, p.image, v.name AS "variantName",
                               c.category_name AS "categoryName", p.is_deleted AS "isDeleted",
                               p.create_by AS "createBy", p.create_date AS "createDate",
                               p.variant_id AS "variantId", p.update_by AS "updateBy",
                               p.update_date AS "updateDate", c.id as "categoryId"
                        FROM tbl_m_product AS p
                        INNER JOIN tbl_m_variant AS v ON p.variant_id = v.id
                        INNER JOIN tbl_m_categories AS c ON v.category_id = c.id
                        WHERE p.is_deleted IS NOT TRUE AND p.id = ?1
                        """, nativeQuery = true)
        Optional<Map<String, Object>> findByNativeQueryId(int id);

        @Query(value = """
                        Select p.id,p.price,p.stock,p.image,
                                v.id as "variantId", v.name AS "variantName",
                                c.id as "categoryId",
                                p.name as "name"
                                ,c.category_name as "categoryName",
                                p.is_deleted as "deleted",
                                p.create_by as "createBy", p.create_date as "createDate",
                                p.update_by as "updateBy", p.update_date as "updateDate"
                        FROM tbl_m_product p
                                inner join tbl_m_variant v on p.variant_id = v.id
                                inner join tbl_m_categories c on v.category_id = c.id
                        WHERE p.is_deleted is false AND (
                                LOWER(p.name) LIKE %?1%
                                OR LOWER(v.name) LIKE %?1%
                                OR LOWER(c.category_name) LIKE %?1%
                        )
                                """, nativeQuery = true)
        Optional<List<Map<String, Object>>> findByFilter(String filter);

        @Query(value = """
                        Select p.id,p.price,p.stock,p.image,
                                v.id as "variantId",
                                v.name AS "variantName",
                                c.id as "categoryId",
                                p.name as "name",
                                c.category_name as "categoryName",
                                p.is_deleted as "deleted",
                                p.create_by as "createBy",
                                p.create_date as "createDate",
                                p.update_by as "updateBy",
                                p.update_date as "updateDate"
                        FROM tbl_m_product p
                                inner join tbl_m_variant v on p.variant_id = v.id
                                inner join tbl_m_categories c on v.category_id = c.id
                        WHERE p.is_deleted is false AND (
                                LOWER(p.name) LIKE %?1%
                                OR LOWER(v.name) LIKE %?1%
                                OR LOWER(c.category_name) LIKE %?1%
                        )
                                """, nativeQuery = true)
        Page<Map<String, Object>> findByPaginationFilter(String filter, Pageable page);
}
