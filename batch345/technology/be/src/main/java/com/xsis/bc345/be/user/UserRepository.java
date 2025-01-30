package com.xsis.bc345.be.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<List<UserModel>> findAllByDeleted(boolean deleted);

    Optional<UserModel> findByIdAndDeleted(Long id, boolean deleted);

    Optional<UserModel> findByEmailEqualsIgnoreCaseAndDeleted(String email, boolean deleted);
}
