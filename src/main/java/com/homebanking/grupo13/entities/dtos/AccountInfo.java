package com.homebanking.grupo13.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// todo dto para enviar info de la cuenta
public class AccountInfo {

  //todo Nro de cuenta desde Account entity
  // private String accountNumber o cbu ; aun inexistente!
  // private BigDecimal accountBalance; aun inexistente!

  //todo Nombre del user y Saldo, desde la entidad User
  private String nameUser;


}
