package com.xsis.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xsis.backend.models.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    Optional<List<OrderDetail>> findByDeleted(boolean deleted);
}
