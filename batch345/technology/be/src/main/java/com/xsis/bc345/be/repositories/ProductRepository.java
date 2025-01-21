package com.xsis.bc345.be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.be.models.Product;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository <Product, Integer> {
    
    
    Optional <List<Product>> findByDeleted(boolean deleted);
    

    @Query(value = "SELECT p.id, p.name, p.image, p.price, p.stock, p.variant_id AS \"variantId\", v.name AS \"variant\", c.category_name AS \"categoryName\", p.is_deleted AS \"deleted\", p.create_by AS \"createBy\",  p.create_date AS \"createDate\", p.update_by AS \"updateBy\", p.update_date AS \"updateDate\" "
        +"FROM tbl_m_product AS p "
		+"INNER JOIN tbl_m_variant AS v ON p.variant_id = v.id "
        +"INNER JOIN tbl_m_categories AS c ON v.category_id = c.id "
        +"WHERE v.is_deleted IS NOT TRUE", nativeQuery = true)
        Optional <List<Map<String, Object>>> findAllNative();
}
