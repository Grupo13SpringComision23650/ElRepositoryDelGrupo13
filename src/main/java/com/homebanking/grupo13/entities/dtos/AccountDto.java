package com.homebanking.grupo13.entities.dtos;

import com.homebanking.grupo13.entities.User;
import com.homebanking.grupo13.entities.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;

    private AccountType type;

    private String cbu;

    private String alias;

    private BigDecimal amount;

    private Long owner_id;
}
