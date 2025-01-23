package com.xsis.bc345.be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.be.models.Product;
import com.xsis.bc345.be.models.Variant;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository <Product, Integer> {
    
    
    Optional <List<Product>> findByDeleted(boolean deleted);

    Optional<Product> findByIdAndDeleted(int id, boolean deleted);

    

    @Query(value = "SELECT p.id, p.name, p.image, p.price, p.stock, p.variant_id AS \"variantId\", v.name AS \"variant\", c.category_name AS \"categoryName\", p.is_deleted AS \"deleted\", p.create_by AS \"createBy\",  p.create_date AS \"createDate\", p.update_by AS \"updateBy\", p.update_date AS \"updateDate\", c.id AS \"categoryId\" "
        +"FROM tbl_m_product AS p "
		+"INNER JOIN tbl_m_variant AS v ON p.variant_id = v.id "
        +"INNER JOIN tbl_m_categories AS c ON v.category_id = c.id "
        +"WHERE v.is_deleted IS NOT TRUE", nativeQuery = true)
        Optional <List<Map<String, Object>>> findAllNative();


    @Query(value = "SELECT p.id, p.name, p.image, p.price, p.stock, p.variant_id AS \"variantId\", v.name AS \"variant\", c.category_name AS \"categoryName\", p.is_deleted AS \"deleted\", p.create_by AS \"createBy\",  p.create_date AS \"createDate\", p.update_by AS \"updateBy\", p.update_date AS \"updateDate\" , c.id AS \"categoryId\""
        +"FROM tbl_m_product AS p "
		+"INNER JOIN tbl_m_variant AS v ON p.variant_id = v.id "
        +"INNER JOIN tbl_m_categories AS c ON v.category_id = c.id "
        +"WHERE v.is_deleted IS NOT TRUE AND LOWER(p.name) LIKE CONCAT('%', :filter, '%')", nativeQuery = true)
        Optional <List<Map<String, Object>>> findAllFilter(@Param ("filter") String filter);

        
        @Query(value = "SELECT p.id, p.name, p.image, p.price, p.stock, p.variant_id AS \"variantId\", v.name AS \"variant\", c.category_name AS \"categoryName\", p.is_deleted AS \"deleted\", p.create_by AS \"createBy\",  p.create_date AS \"createDate\", p.update_by AS \"updateBy\", p.update_date AS \"updateDate\", c.id AS \"categoryId\" "
        +"FROM tbl_m_product AS p "
		+"INNER JOIN tbl_m_variant AS v ON p.variant_id = v.id "
        +"INNER JOIN tbl_m_categories AS c ON v.category_id = c.id "
        +"WHERE v.is_deleted IS NOT TRUE AND p.id = :id", nativeQuery = true)
        Optional <Map<String, Object>> findByIdNative(@Param ("id") int id);
}
