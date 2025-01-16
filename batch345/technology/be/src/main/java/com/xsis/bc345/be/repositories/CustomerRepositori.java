package com.xsis.bc345.be.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.be.models.Customer;

@Repository
public interface CustomerRepositori extends JpaRepository<Customer, Integer> {
    Optional<List<Customer>> findByDeleted(boolean deleted);
    Optional<Customer> findByIdAndDeleted(Integer id, boolean deleted);
    Optional<List<Customer>> findByCustomerNameIgnoreCaseAndDeleted(String categoryName, boolean deleted);
    Optional<List<Customer>> findByCustomerNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(String categoryName, String description, boolean deleted);    
}
