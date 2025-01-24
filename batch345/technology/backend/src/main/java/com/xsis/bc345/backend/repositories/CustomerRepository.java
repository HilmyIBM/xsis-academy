package com.xsis.bc345.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.backend.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
  Optional<List<Customer>> findByDeleted(boolean deleted);
  Optional<Customer> findByIdAndDeleted(int id, boolean deleted);
}
