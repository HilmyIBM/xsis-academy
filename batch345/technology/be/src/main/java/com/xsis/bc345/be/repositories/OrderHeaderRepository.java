package com.xsis.bc345.be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xsis.bc345.be.models.OrderHeader;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {

}
