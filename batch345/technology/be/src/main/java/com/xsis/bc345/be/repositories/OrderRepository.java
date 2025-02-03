package com.xsis.bc345.be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.be.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    
}
