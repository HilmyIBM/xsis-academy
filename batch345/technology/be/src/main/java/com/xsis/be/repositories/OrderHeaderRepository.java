package com.xsis.be.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.be.models.OrderHeader;

@Repository
public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Integer>{
    Optional<List<OrderHeader>>findByDeleted(boolean deleted);
}