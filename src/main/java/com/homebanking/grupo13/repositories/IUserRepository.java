package com.homebanking.grupo13.repositories;

import com.homebanking.grupo13.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
// por convencion la llamo IUserRepository porque es una interface

public interface IUserRepository extends JpaRepository<User,Long> {
  // Utilizo un Query Metod JPA para la verificacion de email exist para saber si ya existe el Usuario en la base
  boolean existsByEmail(String email);


}
