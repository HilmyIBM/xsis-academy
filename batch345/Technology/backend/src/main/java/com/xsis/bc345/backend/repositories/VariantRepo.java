package com.xsis.bc345.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.backend.models.VariantModel;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public interface VariantRepo extends JpaRepository<VariantModel,Integer> {
    Optional<List<VariantModel>> findByIsDeleted(boolean isDeleted);
    Optional<VariantModel>findByIdAndIsDeleted(Integer id,boolean isDeleted);
    
    @Query(value = "SELECT v.id,v.name,v.description,v.category_id AS \"categoryId\",c.category_name AS \"categoryName\", "
        + "v.is_deleted AS \"isDeleted\",v.create_by AS \"createBy\",v.create_date  AS \"createDate\", "
        + "v.update_by AS \"updateBy\",v.update_date AS \"updateDate\" "
        + "FROM tbl_m_variant as v INNER JOIN tbl_m_categories AS c ON v.category_id=c.id"
        + " WHERE v.is_deleted IS NOT TRUE",
        nativeQuery = true
    )
    Optional<List<Map<String,Object>>> findByNativeQuery();
    
}
