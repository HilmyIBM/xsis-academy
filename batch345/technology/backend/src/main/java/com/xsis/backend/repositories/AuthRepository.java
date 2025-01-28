package com.xsis.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xsis.backend.models.Customer;

public interface AuthRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByEmailAndDeleted(String email, boolean deleted);

    Optional<Customer> findByEmailAndPassword(String email, String password);
}
