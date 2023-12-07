package com.homebanking.grupo13.entities.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private Long id;

  @NotBlank(message = "El Nombre de usuario es necesario")
  private String nameUser;

  @NotBlank(message = "El Email es necesario")
  @Email(message="Email invalido")
  private String email;

  @NotBlank(message = "La Clave es necesaria")
  private String password;

  @NotBlank(message = "EL DNI es necesaria")
  @Pattern(regexp="\\d{4,8}",message = "El documento debe tener entre 6 y 8 digitos")
  private String dni;

  private String birthday;

  private String address;

  private boolean enabled; // agregado

  private List<AccountDto> accounts = new ArrayList<>();

}
