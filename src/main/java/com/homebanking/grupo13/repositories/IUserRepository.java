package com.homebanking.grupo13.repositories;

import com.homebanking.grupo13.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
// por convencion la llamo IUserRepository porque es una interface
@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
  // Utilizo un Query Metod JPA para la verificacion de email exist para saber si ya existe el Usuario en la base
  boolean existsByEmail(String email);
  Optional<User> findByEmail(String email);
  Optional<User> findByDni(Long id);
}
