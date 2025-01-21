package com.xsis.bc345.be.variant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VariantRepository extends JpaRepository<VariantModel, Integer> {
    Optional<List<VariantModel>> findAllByDeleted(boolean deleted);

    Optional<VariantModel> findByIdAndDeleted(Integer id, boolean deleted);

    Optional<List<VariantModel>> findAllByDeletedAndCategory_Deleted(boolean deleted, boolean categoryDeleted);

    int countVariantModelByCategory_IdAndDeleted(int categoryId, boolean deleted);
}
