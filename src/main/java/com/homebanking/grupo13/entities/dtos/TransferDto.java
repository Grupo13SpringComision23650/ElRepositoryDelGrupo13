package com.homebanking.grupo13.entities.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TransferDto {

    private Long id;

    @NotNull(message = "La cuenta Destino es necesario")
    private Long accountSourceId;

    @NotNull(message = "La cuenta Destino es necesario")
    private Long accountDestineId;

    @NotNull(message = "La cantidad es necesario")
    private BigDecimal amount;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;
}
