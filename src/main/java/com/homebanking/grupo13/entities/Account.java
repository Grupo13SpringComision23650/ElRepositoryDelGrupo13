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

    private String cbu;

    private String alias;

    @Column(name = "monto")
    private BigDecimal amount;

    @Column(name = "fecha_creacion")
    private LocalDateTime created_at;

    @Column(name = "fecha_modificacion")
    private LocalDateTime updated_at;

    //@ManyToOne
    //private User owner;
    @ManyToOne
    // todo indicamos en que campo estara registrada la relacion
    // todo y éste campo no puede ser nulo por lo que la relacion
    // todo siempre debe existir!!
    // todo eso quiere decir que cuando generemos un nuevo user
    // todo éste debe tener al menos una cuenta!
    @JoinColumn(name = "idUser", nullable = false)
    // todo la relacion es con User y se llama ouner, éste es el nombre de la relacion
    private User owner;

    // todo hacemos la relacion tambien en User a la inversa
}
