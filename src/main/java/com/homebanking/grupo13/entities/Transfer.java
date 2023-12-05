package com.homebanking.grupo13.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "transfers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* TODO: Implementar esto cuando Account este disponible
    private Account accountSource;
    private Account accountDestine;
    */

    @Column(name="amount",scale=2)
    private BigDecimal amount;

    @Column(name="created_at")
    private Timestamp createAt;
}
