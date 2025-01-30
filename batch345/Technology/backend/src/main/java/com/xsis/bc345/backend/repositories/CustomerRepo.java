package com.xsis.bc345.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.backend.models.CustomerModel;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerModel,Integer> {
    Optional<List<CustomerModel>> findByisDeleted(boolean isDeleted);
    Optional<CustomerModel>findByIdAndIsDeleted(Integer id,boolean isDeleted);
    Optional<CustomerModel>findByEmailAndIsDeleted(String email,boolean isDeleted);

    Page<CustomerModel> findByIsDeleted(boolean isDeleted, Pageable pageable);
    Page<CustomerModel> findByEmailIgnoreCaseContainingOrNameIgnoreCaseContainingAndIsDeleted(String email, String name, boolean isDeleted, Pageable pageable);
} 