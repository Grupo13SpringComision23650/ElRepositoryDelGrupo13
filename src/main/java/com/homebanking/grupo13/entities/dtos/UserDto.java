package com.homebanking.grupo13.entities.dtos;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private Long id;
  private String nameUser;
  private String email;
  private String password;
  private String dni;
  private String birthday;
  private String address;
  private boolean enabled; // agregado

  private List<AccountDto> accounts = new ArrayList<>();

}
