package com.homebanking.grupo13.entities;

import com.homebanking.grupo13.entities.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cuentas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_cuenta")
    private AccountType type;

    @Column(unique = true, nullable = false)
    private String cbu;

    @Column(unique = true, nullable = false)
    private String alias;

    @Column(name = "monto", nullable = false )
    private BigDecimal amount;

    @Column(name = "fecha_creacion")
    private LocalDateTime created_at;

    @Column(name = "fecha_modificacion")
    private LocalDateTime updated_at;

    @ManyToOne
    private User owner;
}
