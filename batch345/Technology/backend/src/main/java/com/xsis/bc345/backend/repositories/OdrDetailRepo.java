package com.xsis.bc345.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.backend.models.OdrDetailModel;
import java.util.List;
import java.util.Optional;


@Repository
public interface OdrDetailRepo extends JpaRepository<OdrDetailModel,Integer> {
    Optional<List<OdrDetailModel>> findByIsDeleted(boolean isDeleted);   
}
