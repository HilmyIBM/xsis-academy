package com.xsis.bc345.be.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xsis.bc345.be.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
        Optional<List<Product>> findByDeleted(boolean deleted);

        Optional<List<Product>> findByCategoryContainsIgnoreCaseOrNameContainsIgnoreCaseAndDeleted(String Category,
                        String Name,
                        boolean deleted);

        @Query(value = """
                        SELECT a.*, b.name as "variantName", c.category_name as "categoryName" from tbl_m_product a
                        inner join tbl_m_variant b on b.id = a.variant_id
                        inner join tbl_m_categories c on c.id = b.category_id
                        where a.is_deleted is not true;
                        """, nativeQuery = true)
        Optional<List<Map<String, Object>>> findAllData();
}
