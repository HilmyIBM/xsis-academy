package com.xsis.bc345.be.variant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VariantRepository extends JpaRepository<VariantModel, Integer> {
    Optional<List<VariantModel>> findAllByDeleted(boolean deleted);

    Optional<VariantModel> findByIdAndDeleted(Integer id, boolean deleted);


    @Query(nativeQuery = true,
            value = """
                SELECT \s
                    v.*
                FROM\s
                    tbl_m_variant AS v\s
                    INNER JOIN public.tbl_m_categories AS c on c.id = v.category_id
                WHERE\s
                        v.is_deleted IS FALSE AND\s
                        c.is_deleted IS FALSE AND\s
                        c.id = :id
           """
    )
    Optional<List<VariantModel>> nativeFindAllVariantsByCategoryId(@Param("id") int id);

    Optional<List<VariantModel>> findAllByDeletedAndCategory_Deleted(boolean deleted, boolean categoryDeleted);

    Page<VariantModel> findAllByDeleted(boolean deleted, Pageable pageable);

    int countAllByCategory_DeletedAndId(boolean categoryDeleted, int id);
}
