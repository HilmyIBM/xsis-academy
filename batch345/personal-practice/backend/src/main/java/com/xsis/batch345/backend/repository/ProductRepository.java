package com.xsis.batch345.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xsis.batch345.backend.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
  
  @Query(value=
    """
    SELECT
      p.id as \"id\",
      p.name as \"name\",
      p.price as \"price\",
      p.stock as \"stock\",
      p.image as \"image\",
      p.variant_id as \"variant_id\",
      v.name as \"variant_name\",
      v.category_id as \"category_id\",
      c.category_name as \"category_name\",
      v.is_deleted as \"is_deleted\",
      v.create_by as \"create_by\",
      v.create_date as \"create_date\",
      v.update_by as \"update_by\",
      v.update_date as \"update_date\"
    FROM tbl_m_product AS p 
    INNER JOIN tbl_m_variant AS v ON p.variant_id = v.id
    INNER JOIN tbl_m_categories AS c ON v.category_id = c.id
    WHERE p.is_deleted IS FALSE;
    """,
    nativeQuery = true
  )
  Optional<List<Product>> nativeFindAll();

  @Query(value=
    """
    SELECT
      p.id as \"id\",
      p.name as \"name\",
      p.price as \"price\",
      p.stock as \"stock\",
      p.image as \"image\",
      p.variant_id as \"variant_id\",
      v.name as \"variant_name\",
      v.category_id as \"category_id\",
      c.category_name as \"category_name\",
      v.is_deleted as \"is_deleted\",
      v.create_by as \"create_by\",
      v.create_date as \"create_date\",
      v.update_by as \"update_by\",
      v.update_date as \"update_date\"
    FROM tbl_m_product AS p 
    INNER JOIN tbl_m_variant AS v ON p.variant_id = v.id
    INNER JOIN tbl_m_categories AS c ON v.category_id = c.id
    WHERE p.id = :id AND p.is_deleted IS FALSE;
    """,
    nativeQuery = true)
  Optional<Product> nativeFindById(@Param("id") Integer id);

  @Query(value=
    """
    SELECT
      p.id as \"id\",
      p.name as \"name\",
      p.price as \"price\",
      p.stock as \"stock\",
      p.image as \"image\",
      p.variant_id as \"variant_id\",
      v.name as \"variant_name\",
      v.category_id as \"category_id\",
      c.category_name as \"category_name\",
      v.is_deleted as \"is_deleted\",
      v.create_by as \"create_by\",
      v.create_date as \"create_date\",
      v.update_by as \"update_by\",
      v.update_date as \"update_date\"
    FROM tbl_m_product AS p 
    INNER JOIN tbl_m_variant AS v ON p.variant_id = v.id
    INNER JOIN tbl_m_categories AS c ON v.category_id = c.id
    WHERE p.is_deleted IS FALSE AND (LOWER(p.name) LIKE CONCAT('%' + :filter + '%') OR LOWER(v.name) LIKE CONCAT('%' + :filter + '%') OR LOWER(c.category_name) LIKE CONCAT('%' + :filter + '%')); 
    """,
    nativeQuery = true)
  Optional<List<Product>> nativeFindByFilter(@Param("filter") String filter);

}
