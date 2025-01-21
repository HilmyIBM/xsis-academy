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
            WHERE p.is_deleted IS FALSE;
        """,
        nativeQuery = true
    )
    Optional<List<Map<String, Object>>> findProduct();
    
}
