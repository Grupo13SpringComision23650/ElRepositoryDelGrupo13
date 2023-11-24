package com.homebanking.grupo13.entities.dtos;

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

    /* TODO: Implementar esto cuando Account este disponible
    private Account accountSource;
    private Account accountDestine;
    */

    private BigDecimal amount;

    private Timestamp createAt;
}
