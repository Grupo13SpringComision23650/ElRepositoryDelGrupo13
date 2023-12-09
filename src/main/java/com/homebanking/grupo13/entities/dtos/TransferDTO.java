package com.homebanking.grupo13.entities.dtos;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

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

    private LocalDateTime created_at;

    private LocalDateTime updated_at;
}
