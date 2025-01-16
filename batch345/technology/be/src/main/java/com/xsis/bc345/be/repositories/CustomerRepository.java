package com.xsis.bc345.be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.be.models.Customer;
import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository <Customer, Integer> {

    Optional <List<Customer>> findByDeleted(boolean deleted);
    Optional <Customer> findByIdAndDeleted(int id, boolean deleted);
    Optional <List<Customer>> findByNameContainsAndDeleted(String name, boolean deleted);
    Optional <List<Customer>> findByEmailContainsAndDeleted(String email, boolean deleted);

    
}
