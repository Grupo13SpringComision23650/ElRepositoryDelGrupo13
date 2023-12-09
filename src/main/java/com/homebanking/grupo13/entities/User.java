package com.homebanking.grupo13.entities;


import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
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
  private Long dni;

  private String birthday;
  private String address;

  @ColumnDefault(value = "0")
  private boolean enabled;

  @CreationTimestamp
  private Timestamp create_at;

  @UpdateTimestamp
  private Timestamp update_at;

  @OneToMany(mappedBy = "owner")
  private List<Account> accounts = new ArrayList<>();
}
