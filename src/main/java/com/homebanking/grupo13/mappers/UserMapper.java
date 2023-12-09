package com.homebanking.grupo13.mappers;

import com.homebanking.grupo13.entities.Account;
import com.homebanking.grupo13.entities.User;
import com.homebanking.grupo13.entities.dtos.AccountDto;
import com.homebanking.grupo13.entities.dtos.UserDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class UserMapper {

  public static UserDto userToDto(User user) {
    UserDto userDto = new UserDto();
    userDto.setId(user.getId());
    userDto.setNameUser(user.getNameUser());
    userDto.setEmail(user.getEmail());
    // Consigna 9) "no se debe mostrar la contrasena"
    //userDto.setPassword(user.getPassword());
    userDto.setPassword("*************");
    userDto.setDni(user.getDni());
    userDto.setBirthday(user.getBirthday());
    userDto.setAddress(user.getAddress());
    userDto.setEnabled(user.isEnabled());

    List<AccountDto> accountDtos = new ArrayList<>();
    for (Account account : user.getAccounts()) {
      AccountDto accountDto = AccountMapper.accountToDto(account);
      accountDtos.add(accountDto);
    }
    userDto.setAccounts(accountDtos);

    return userDto;
  }

  public static User dtoToUser(UserDto userDto) {
    User user = new User();
    user.setId(userDto.getId());
    user.setNameUser(userDto.getNameUser());
    user.setEmail(userDto.getEmail());
    user.setPassword(userDto.getPassword());
    user.setDni(userDto.getDni());
    user.setBirthday(userDto.getBirthday());
    user.setAddress(userDto.getAddress());
    user.setEnabled(userDto.isEnabled());

    List<Account> accounts = new ArrayList<>();
    for (AccountDto accountDto : userDto.getAccounts()) {
      Account account = AccountMapper.dtoToAccount(accountDto);
      accounts.add(account);
    }
    user.setAccounts(accounts);

    return user;
  }
}
