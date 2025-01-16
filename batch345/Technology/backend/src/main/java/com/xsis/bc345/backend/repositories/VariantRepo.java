package com.xsis.bc345.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.backend.models.VariantModel;
import java.util.List;
import java.util.Optional;


@Repository
public interface VariantRepo extends JpaRepository<VariantModel,Integer> {
    Optional<List<VariantModel>> findByIsDeleted(boolean isDeleted);
    
}
