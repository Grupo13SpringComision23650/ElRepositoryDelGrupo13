package com.homebanking.grupo13.entities.dtos;

import com.homebanking.grupo13.entities.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @NotNull(message = "EL tipo es necesario (numero)")
    private AccountType type;

    @NotBlank(message = "El CBU es necesario")
    @Pattern(regexp="\\d{22}",message = "EL CBU es un numero de 22 digitos")
    private String cbu;

    @NotBlank(message = "El Alias es necesario")
    private String alias;

    @NotNull(message = "La cantidad es necesaria")
    private BigDecimal amount;

    private boolean enabled;

    @NotNull(message = "El onwer_id es necesario")
    private Long owner_id;
}
