package com.homebanking.grupo13.services;

import com.homebanking.grupo13.entities.Account;
import com.homebanking.grupo13.entities.User;
import com.homebanking.grupo13.entities.dtos.AccountDto;
import com.homebanking.grupo13.entities.dtos.UserDto;
import com.homebanking.grupo13.exceptions.UserNotFoundException;
import com.homebanking.grupo13.mappers.AccountMapper;
import com.homebanking.grupo13.mappers.UserMapper;
import com.homebanking.grupo13.repositories.AccountRepository;
import com.homebanking.grupo13.repositories.IUserRepository;
import com.homebanking.grupo13.entities.enums.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {
  @Autowired
  private IUserRepository userRepository;
  @Autowired
  private AccountRepository accountRepository;

  // listar por ID
  public UserDto getUserById(Long id){
    User user = userRepository.findById(id)
            .orElseThrow(()->new UserNotFoundException("Usuario no encontrado"));
    return UserMapper.userToDto(user);
  }

  public UserDto getUserByDni(Long dni) {
    User user = userRepository.findByDni(dni)
            .orElseThrow(()-> new UserNotFoundException("Usuario no encontrado"));
    return UserMapper.userToDto(user);
  }

  //listar Todo (activos e inactivos)
  public List<UserDto> getUsers() {
    return userRepository.findAll().stream()
            .map(UserMapper::userToDto)
            .collect(Collectors.toList());
  }
  public UserDto createUser(UserDto userDto) {
    User user=null;
    Optional<User> optionalUser=userRepository.findByDni(userDto.getDni());

    // En caso que exista el usuario (por dni), reativar y aclualizar datos
    if(optionalUser.isPresent()){
      user=optionalUser.get();
      userDto.setEnabled(true);
      userDto.setId(user.getId());
      return this.updateUser(userDto);
    }

    // Sino generar un nuevo usuario
    userDto.setEnabled(true);
    user=UserMapper.dtoToUser(userDto);
    User savedUser = userRepository.save(user);

    // consigna 8) crear una cuenta automaticamete
    Account account=new Account();
    account.setAmount(BigDecimal.ZERO);
    account.setType(AccountType.CAJA_AHORRO_PESOS); // por defecto
    account.setAlias("codo.a.codo.alias."+(int)(Math.random()*10000));
    account.setCbu("00000123"+(int)(Math.random()*Integer.MAX_VALUE));
    account.setOwner(savedUser);
    account.setEnabled(true);
    Account newAccount=accountRepository.save(account);


    final AccountDto accountDto=AccountMapper.accountToDto(newAccount);
    final UserDto savedUserDto=UserMapper.userToDto(savedUser);
    savedUserDto.setAccounts(List.of(accountDto)); // arreglado

    return savedUserDto;
  }

// update user

  public UserDto updateUser(UserDto userDto) {
    User user = userRepository.findById(userDto.getId())
            .orElseThrow(()->new UserNotFoundException("Usuario no encontrado"));

    if(user.getNameUser()!=null) {
      user.setNameUser(userDto.getNameUser());
    }
    if(user.getEmail()!=null) {
      user.setEmail(userDto.getEmail());
    }
    if(user.getPassword() != null) {
      user.setPassword(userDto.getPassword());
    }
    if(user.getDni() != null) {
      user.setDni(userDto.getDni());
    }
    if(user.getBirthday() != null) {
      user.setBirthday(userDto.getBirthday());
    }
    if(user.getAddress() != null) {
      user.setAddress(userDto.getAddress());
    }
    user.setEnabled(userDto.isEnabled());
    System.out.println("UserAccount.updateUser.updatedUser:user.getAccounts() => "+user.getAccounts());
    // TODO: si accounts (dto) es enviado por el usuario, son agregados a la lista de accounts

    for(Account acc:user.getAccounts()) {
      acc.setEnabled(true);
    }
    User updatedUser = userRepository.save(user);
    return UserMapper.userToDto(updatedUser);
  }

  //Solo hay que deshabilitarlo
  public UserDto deleteUser(Long id){
    User user = userRepository.findById(id)
            .orElseThrow(()->new UserNotFoundException("Usuario no encontrado"));

    user.setEnabled(false);

    // dehabilitar todas las cuentas
    for(Account acc:user.getAccounts()){
      acc.setEnabled(false);
    }
    User userSaved=userRepository.save(user);
    return UserMapper.userToDto(userSaved);
  }
}

