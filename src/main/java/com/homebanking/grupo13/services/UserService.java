package com.homebanking.grupo13.services;

import com.homebanking.grupo13.entities.Account;
import com.homebanking.grupo13.entities.User;
import com.homebanking.grupo13.entities.dtos.AccountDto;
import com.homebanking.grupo13.entities.dtos.UserDto;
import com.homebanking.grupo13.mappers.AccountMapper;
import com.homebanking.grupo13.mappers.UserMapper;
import com.homebanking.grupo13.repositories.AccountRepository;
import com.homebanking.grupo13.repositories.IUserRepository;
import com.homebanking.grupo13.entities.enums.AccountType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homebanking.grupo13.mappers.AccountMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService {


  private final IUserRepository userRepository;

  private final AccountRepository accountRepository;

  // listar por ID
  public UserDto getUserById(Long id){
    User user= userRepository.findById(id).get();
    return UserMapper.userToDto(user);
  }

  //listar Todo

  public List<UserDto> getUsers() {
    return userRepository.findAll().stream()
            .map(UserMapper::userToDto)
            .collect(Collectors.toList());
  }

//crear user
  public UserDto createUser(UserDto userDto) {
    User user = UserMapper.dtoToUser(userDto);
    user.setEnabled(true);
    // Falta validaciones aca y en todos los services
    User savedUser = userRepository.save(user);

    // esto FUNCIONO !
    final LocalDateTime now=LocalDateTime.now();
    Account account=new Account();
    account.setAmount(BigDecimal.ZERO);
    account.setType(AccountType.CAJA_AHORRO_PESOS);
    account.setAlias("codo.a.codo.alias."+(int)(Math.random()*10000));
    account.setCbu("00000123"+(int)(Math.random()*Integer.MAX_VALUE));
    account.setCreated_at(now);
    account.setUpdated_at(now);
    account.setOwner(savedUser);
    Account newAccount=accountRepository.save(account);


    final AccountDto accountDto=AccountMapper.accountToDto(newAccount);
    final UserDto savedUserDto=UserMapper.userToDto(savedUser);
    savedUserDto.setAccounts(List.of(accountDto));

    return savedUserDto;
  }

// update user
  /*public UserDto updateUser(UserDto userDto) {
    Optional<User> optionalUser = userRepository.findById(userDto.getId());

    if (optionalUser.isPresent()) {
      User existingUser = optionalUser.get();
      existingUser.setNameUser(userDto.getNameUser());
      existingUser.setEmail(userDto.getEmail());
      existingUser.setPassword(userDto.getPassword());
      existingUser.setDni(userDto.getDni());
      existingUser.setBirthday(userDto.getBirthday());
      existingUser.setAddress(userDto.getAddress());
      existingUser.setEnabled(userDto.isEnabled());

      // Update accounts if needed
      List<Account> accounts = new ArrayList<>();
      if (userDto.getAccounts() != null) {
        for (AccountDto accountDto : userDto.getAccounts()) {
          Account account = AccountMapper.dtoToAccount(accountDto);
          account.setOwner(existingUser);
          accounts.add(account);
        }
      }
      existingUser.setAccounts(accounts);

      User updatedUser = userRepository.save(existingUser);
      return UserMapper.userToDto(updatedUser);
    } else {
      // Handle case when user with given ID is not found
      return null;
    }
  }*/

  public UserDto updateUser(Long id, UserDto dto) {
    if (userRepository.existsById(id)){
      User userToModify = userRepository.findById(id).get();

      if (dto.getNameUser() != null){
        userToModify.setNameUser(dto.getNameUser());
      }

      // TODO: agregar validacion de email existente
      if (dto.getEmail() != null){
        userToModify.setEmail(dto.getEmail());
      }

      if (dto.getPassword() != null){
        userToModify.setPassword(dto.getPassword());
      }

      if (dto.getDni() != null){
        userToModify.setDni(dto.getDni());
      }

      if (dto.getAddress() != null){
        userToModify.setAddress(dto.getAddress());
      }
      if (dto.getBirthday() != null){
        userToModify.setBirthday(dto.getBirthday());
      }

      userToModify.setUpdate_at(LocalDateTime.now());

      User userModified = userRepository.save(userToModify);

      return UserMapper.userToDto(userModified);
    }

    return null;
  }
  public void deleteUser(Long id){
    userRepository.deleteById(id);
  }

}
