package com.minprobe.back_end.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minprobe.back_end.models.BloodGroup;

@Repository
public interface BloodGroupRepository extends JpaRepository<BloodGroup, Integer> {
    Optional<List<BloodGroup>> findByDelete(boolean deleted);
    Optional<BloodGroup> findByIdAndDelete(int id, boolean deleted);
    Optional<List<BloodGroup>> findByCodeContainsIgnoreCaseAndDelete(String code, boolean deleted);
}
