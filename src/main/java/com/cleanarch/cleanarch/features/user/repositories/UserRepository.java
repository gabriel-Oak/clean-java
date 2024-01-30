package com.cleanarch.cleanarch.features.user.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleanarch.cleanarch.features.user.entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findUserByEmail(String email);
}
