package com.xsis.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.backend.models.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Integer> {
    Optional<List<Variant>> findByDeleted(boolean deleted);
}
