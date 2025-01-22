package com.xsis.bc345.be.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.be.models.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Integer>  {
    // Optional<List<Variant>> findByDeleted(boolean deleted);
    // @Query("SELECT v FROM Variant v LEFT JOIN FETCH v.category")
    // List<Variant> findAllVariantsWithCategoryAndDeleted(boolean deleted);
    Optional<Variant> findByIdAndDeleted(int id, boolean deleted);

    @Query(value = 
        """
            SELECT
                v.*,
                c.category_name AS "categoryName"
            FROM tbl_m_variant v
            INNER JOIN tbl_m_categories c ON v.category_id = c.id
            WHERE v.is_deleted IS FALSE 
                AND c.is_deleted IS FALSE 
            ORDER BY v.id;
        """, 
        nativeQuery = true
    )
    Optional<List<Map<String, Object>>> findAllVariant();

}
