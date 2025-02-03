package com.xsis.bc345.be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xsis.bc345.be.models.OrderDetail;

public interface OrderDetailsRepository extends JpaRepository<OrderDetail, Long>{

}
