package com.xsis.batch345.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xsis.batch345.backend.model.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Integer> {
  
  @Query(value=
    """
    SELECT
      v.id as \"id\",
      v.name as \"name\",
      v.description as \"description\",
      v.category_id as \"category_id\",
      c.category_name as \"category_name\",
      v.is_deleted as \"is_deleted\",
      v.create_by as \"create_by\",
      v.create_date as \"create_date\",
      v.update_by as \"update_by\",
      v.update_date as \"update_date\"
    FROM
    tbl_m_variant AS v INNER JOIN tbl_m_categories AS c ON v.category_id = c.id
    WHERE v.is_deleted IS FALSE;
    """, 
    nativeQuery = true
  )
  Optional<List<Variant>> nativeFindAll();
}
