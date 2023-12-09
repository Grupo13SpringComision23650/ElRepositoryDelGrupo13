package com.homebanking.grupo13.entities.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class TransferDTO {

    private Long id;

    @NotNull
    private Long accountSourceId;

    @NotNull
    private Long accountDestineId;

    @NotNull
    private BigDecimal amount;

    private Timestamp created_at;

    private Timestamp updated_at;
}
