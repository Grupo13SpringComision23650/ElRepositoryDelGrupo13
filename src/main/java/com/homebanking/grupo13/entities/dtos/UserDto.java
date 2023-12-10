package com.homebanking.grupo13.entities.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;
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
    @Email(message = "Email invalido")
    private String email;

    @NotBlank(message = "La Clave es necesaria")
    private String password;

    @Range(max=99999999,message="El Dni tiene 8 digitos como maximo")
    private Long dni;

    private String birthday;

    private String address;

    private Boolean enabled; // agregado

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private List<AccountDto> accounts = new ArrayList<>();

}
