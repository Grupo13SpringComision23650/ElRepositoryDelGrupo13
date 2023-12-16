package com.homebanking.grupo13.entities;

import com.homebanking.grupo13.entities.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ColumnDefault("0")
    private AccountType type;

    @Column(unique = true, nullable = false)
    private String cbu;

    @Column(unique = true, nullable = false)
    private String alias;

    @Column(nullable = false )
    private BigDecimal amount;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    @ColumnDefault("true")
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User owner;
}
