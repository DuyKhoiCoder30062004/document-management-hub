package com.saigontechnologyintern.document_management.userManagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserManagerRepository extends JpaRepository<UserManager, Integer> {
    boolean existsByEmail(String email);
//    required by JWT/BCrypt auth for login and registration uniqueness checks.
    Optional<UserManager> findByEmail(String email);
}
