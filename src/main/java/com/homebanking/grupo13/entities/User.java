package com.homebanking.grupo13.entities;


import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name_User", nullable = false)
  private String nameUser;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(unique = true, nullable = false)
  private String dni;
  private String birthday;
  private String address;

  private boolean enabled;

  @CreationTimestamp
  private LocalDateTime create_at;

  @UpdateTimestamp
  private LocalDateTime update_at;
 // relacion
  @OneToMany(mappedBy = "owner")
  private List<Account> accounts = new ArrayList<>();
}
