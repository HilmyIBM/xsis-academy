package com.xa.pos313.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xa.pos313.models.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
    
}
