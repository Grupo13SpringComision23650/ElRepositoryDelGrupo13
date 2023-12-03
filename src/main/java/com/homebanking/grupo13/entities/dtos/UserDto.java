package com.homebanking.grupo13.entities.dtos;

import com.homebanking.grupo13.entities.enums.AccountType;
import com.homebanking.grupo13.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
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
  private Status status;
  private LocalDateTime createAt;
  private LocalDateTime modifiedAt;

}
