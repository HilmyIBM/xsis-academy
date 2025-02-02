package com.xsis.bc345.be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.be.models.OrderHeader;

@Repository
public interface OrderRepository extends JpaRepository<OrderHeader, Long>{
    
}
