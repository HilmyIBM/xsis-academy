package com.xsis.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xsis.backend.models.OrderHeader;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Integer> {
    Optional<List<OrderHeader>> findByDeleted(boolean deleted);
}
