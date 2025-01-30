package com.xsis.bc345.be.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.be.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<List<Customer>> findByDeleted(boolean deleted);

    // for Searching
    Optional<List<Customer>> findByEmailContainsIgnoreCaseOrNameContainsIgnoreCaseAndDeleted(String email, String name, boolean deleted);

    // for login
    Optional<Customer> findByEmailIgnoreCaseAndDeleted(String email, boolean deleted);
}