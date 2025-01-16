package com.xsis.bc345.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.bc345.backend.models.OdrHeaderModel;
import java.util.List;
import java.util.Optional;


@Repository
public interface  OdrHeaderRepo extends JpaRepository<OdrHeaderModel,Integer> {
    Optional<List<OdrHeaderModel>> findByIsDeleted(boolean isDeleted);
}
