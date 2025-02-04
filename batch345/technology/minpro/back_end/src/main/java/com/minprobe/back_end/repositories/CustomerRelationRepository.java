package com.minprobe.back_end.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minprobe.back_end.models.CustomerRelation;

@Repository
public interface CustomerRelationRepository extends JpaRepository<CustomerRelation, Integer> {
    Optional<List<CustomerRelation>> findByDelete(boolean deleted);
}
