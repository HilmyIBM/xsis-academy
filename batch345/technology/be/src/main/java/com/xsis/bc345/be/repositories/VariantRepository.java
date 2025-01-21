package com.xsis.bc345.be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.be.models.Variant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Integer> {
    Optional<Variant> findByIdAndDeleted(int id, boolean isDeleted);
    Optional<List<Variant>> findByDeleted(boolean isDeleted);
    Optional<List<Variant>> findByNameContainsIgnoreCaseAndDeleted(String variantName, boolean isDeleted);
    Optional<List<Variant>> findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(String variantName, String description, boolean isDeleted);

    @Query(
        value=
            "SELECT v.id, v.name, v.description, v.category_id AS \"categoryId\", c.category_name AS \"categoryName\", "
            + "v.is_deleted AS \"deleted\", v.create_by AS \"createBy\", v.create_date AS \"createDate\", "
            + "v.update_by AS \"updateBy\", v.update_date AS \"updateDate\" "
            + "FROM tbl_m_variant AS v INNER JOIN tbl_m_categories AS c ON v.category_id=c.id "
            + "WHERE v.is_deleted IS NOT TRUE",
        nativeQuery = true
    )
    Optional<List<Map<String, Object>>> findAllNative();

    @Query(
        value=
            "SELECT v.id, v.name, v.description, v.category_id AS \"categoryId\", c.category_name AS \"categoryName\", "
            + " v.is_deleted AS \"deleted\", v.create_by AS \"createBy\", v.create_date AS \"createDate\", "
            + " v.update_by AS \"updateBy\", v.update_date AS \"updateDate\" "
            + "FROM tbl_m_variant AS v INNER JOIN tbl_m_categories AS c ON v.category_id=c.id "
            + "WHERE v.is_deleted IS NOT TRUE "
            + " AND v.id = :id",
        nativeQuery = true
        )
    Optional<Map<String, Object>> findByIdNative(@Param("id") int id);
}
