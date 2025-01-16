package com.xsis.bc345.be.order.detail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailModel, Long> {

    Optional<List<OrderDetailModel>> findAllByDeleted(boolean deleted);

    Optional<OrderDetailModel> findByIdAndDeleted(Long id, boolean deleted);

}
