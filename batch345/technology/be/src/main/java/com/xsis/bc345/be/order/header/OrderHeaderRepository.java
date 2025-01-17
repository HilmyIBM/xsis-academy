package com.xsis.bc345.be.order.header;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderHeaderRepository extends JpaRepository<OrderHeaderModel, Long> {

    Optional<List<OrderHeaderModel>> findAllByDeleted(boolean deleted);

    Optional<OrderHeaderModel> findByIdAndDeleted(Long id, boolean deleted);

}
