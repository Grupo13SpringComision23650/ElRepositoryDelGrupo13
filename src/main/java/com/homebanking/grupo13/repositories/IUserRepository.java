package com.homebanking.grupo13.repositories;

import com.homebanking.grupo13.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
  boolean existsByEmail(String email);
}
