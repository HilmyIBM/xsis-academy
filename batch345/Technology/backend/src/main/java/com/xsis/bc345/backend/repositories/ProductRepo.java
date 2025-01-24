package com.xsis.bc345.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.backend.models.ProductModel;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public interface  ProductRepo extends JpaRepository<ProductModel,Integer> {
    Optional<List<ProductModel>> findByIsDeleted(boolean isDeleted);
    Optional<ProductModel>findByIdAndIsDeleted(Integer id,boolean isDeleted);

    
    @Query(value = "SELECT p.id,p.name,p.price,p.stock,p.image,v.name AS \"variantName\",c.category_name AS\"categoryName\", "
    + "p.is_deleted AS \"isDeleted\",p.create_by AS \"createBy\",p.create_date  AS \"createDate\",p.variant_id AS\"variantId\", "
    + "p.update_by AS \"updateBy\",p.update_date AS \"updateDate\" "
    + "FROM tbl_m_product as p INNER JOIN tbl_m_variant AS v ON p.variant_id=v.id INNER JOIN tbl_m_categories AS c on v.category_id = c.id"
    + " WHERE p.is_deleted IS NOT TRUE AND p.id=?1",
    nativeQuery = true
    )
    Optional<List<Map<String,Object>>> findByNativeQueryId(int id);

    @Query(value = "SELECT p.id,p.name,p.price,p.stock,p.image,v.name AS \"variantName\",c.category_name AS\"categoryName\", "
    + "p.is_deleted AS \"isDeleted\",p.create_by AS \"createBy\",p.create_date  AS \"createDate\", "
    + "p.update_by AS \"updateBy\",p.update_date AS \"updateDate\" "
    + "FROM tbl_m_product as p INNER JOIN tbl_m_variant AS v ON p.variant_id=v.id INNER JOIN tbl_m_categories AS c on v.category_id = c.id"
    + " WHERE p.is_deleted IS NOT TRUE AND v.is_deleted IS NOT TRUE",
    nativeQuery = true
    )
    Optional<List<Map<String,Object>>> findByNativeQuery();

    @Query(value ="""
    SELECT p.id,p.name,p.price,p.stock,p.image,v.name AS "variantName",c.category_name AS"categoryName",
    p.is_deleted AS "isDeleted",p.create_by AS "createBy",p.create_date  AS "createDate",
    p.update_by AS "updateBy",p.update_date AS "updateDate"
    FROM tbl_m_product as p INNER JOIN tbl_m_variant AS v ON p.variant_id=v.id INNER JOIN tbl_m_categories AS c on v.category_id = c.id
    WHERE p.is_deleted IS NOT TRUE AND (
            LOWER(p.name) LIKE %:filter% OR
            LOWER(v.name) LIKE %:filter% OR
            LOWER(c.category_name) LIKE %:filter% 
            )
            """
    ,nativeQuery = true)
    Optional<List<Map<String,Object>>> findByfilter(String filter);

    Page<ProductModel> findByIsDeleted(boolean isDeleted,Pageable pageable);

}