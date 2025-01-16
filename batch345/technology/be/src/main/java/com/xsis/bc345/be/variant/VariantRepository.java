package com.xsis.bc345.be.variant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VariantRepository extends JpaRepository<VariantModel, Long> {
    Optional<List<VariantModel>> findAllByDeleted(boolean deleted);

    Optional<VariantModel> findByIdAndDeleted(Long id, boolean deleted);
}
