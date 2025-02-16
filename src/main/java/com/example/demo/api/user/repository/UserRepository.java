package com.example.demo.api.user.repository;

import com.example.demo.api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String loginEmail);

    boolean existsByEmail(String loginEmail);

    Optional<User> findByEmailAndPassword(String email, String password);
}
