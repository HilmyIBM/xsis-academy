package com.xsis.bc345.be.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.be.models.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository <OrderDetail, Integer> {

        Optional<List<OrderDetail>> findByDeleted(boolean deleted);

}