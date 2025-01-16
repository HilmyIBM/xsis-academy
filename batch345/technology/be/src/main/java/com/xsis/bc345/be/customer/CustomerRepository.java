package com.xsis.bc345.be.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {
    Optional<List<CustomerModel>> findAllByDeleted(boolean deleted);

    Optional<CustomerModel> findByIdAndDeleted(Long id, boolean deleted);
}
