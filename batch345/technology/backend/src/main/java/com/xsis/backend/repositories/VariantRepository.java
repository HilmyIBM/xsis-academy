package com.xsis.backend.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xsis.backend.models.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Integer> {
    Optional<List<Variant>> findByDeleted(boolean deleted);

    Optional<Variant> findByIdAndDeleted(int id, boolean deleted);

    @Query(
        value =
            "SELECT v.id, v.name, v.category_id AS \"categoryId\", c.category_name AS \"categoryName\", v.description, "
            + "v.is_deleted AS \"deleted\", v.create_by AS \"createBy\", v.create_date AS \"createDate\", "
            + "v.update_by AS \"updateBy\", v.update_date AS \"updateDate\" "
            + "FROM tbl_m_variant AS v INNER JOIN tbl_m_categories AS c ON v.category_id = c.id "
            + "WHERE v.is_deleted IS NOT TRUE",
    nativeQuery = true)
    Optional<List<Map<String, Object>>> findAllNative();
}
