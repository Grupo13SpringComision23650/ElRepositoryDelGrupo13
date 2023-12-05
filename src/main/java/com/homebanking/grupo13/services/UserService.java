package com.homebanking.grupo13.services;

import com.homebanking.grupo13.entities.Account;
import com.homebanking.grupo13.entities.User;
import com.homebanking.grupo13.entities.dtos.AccountDto;
import com.homebanking.grupo13.entities.dtos.UserDto;
import com.homebanking.grupo13.mappers.AccountMapper;
import com.homebanking.grupo13.mappers.UserMapper;
import com.homebanking.grupo13.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

  @Autowired
  private IUserRepository userRepository;

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
    User savedUser = userRepository.save(user);
    return UserMapper.userToDto(savedUser);
  }
// update user
  public UserDto updateUser(UserDto userDto) {
    Optional<User> optionalUser = userRepository.findById(userDto.getId());

    if (optionalUser.isPresent()) {
      User existingUser = optionalUser.get();
      existingUser.setNameUser(userDto.getNameUser());
      existingUser.setEmail(userDto.getEmail());
      existingUser.setPassword(userDto.getPassword());
      existingUser.setDni(userDto.getDni());
      existingUser.setBirthday(userDto.getBirthday());
      existingUser.setAddress(userDto.getAddress());
      existingUser.setStatus(userDto.isStatus());

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
  }
  // metodo delete no se hace porque no se puede borrar el user
  // estoy trabajando en el metodo para hacer automaticamente la cuenta al dar de alta un usar..



}
