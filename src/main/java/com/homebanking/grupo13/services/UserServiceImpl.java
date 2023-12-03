package com.homebanking.grupo13.services;

import com.homebanking.grupo13.entities.User;
import com.homebanking.grupo13.entities.dtos.AccountInfo;
import com.homebanking.grupo13.entities.dtos.BankResponse;
import com.homebanking.grupo13.entities.dtos.UserDto;
import com.homebanking.grupo13.repositories.IUserRepository;
import com.homebanking.grupo13.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService{

  public IUserRepository userRepository;


  /**
   * Creamos un User - guardando el nuevo usuario en la DB
   * Chequeamos que el usuario ya exista por el email!
     */
  public BankResponse createUser(UserDto userDto) {

    if (userRepository.existsByEmail(userDto.getEmail())) {
      BankResponse response = BankResponse.builder()
              .responseCode(Utils.USER_EXISTS_CODE)
              .responseMessage(Utils.USER_EXISTS_MESSAGE)
              .build();
      return response;
    }
    User newUser = User.builder()
            .nameUser(userDto.getNameUser())
            .email(userDto.getEmail())
            .password(userDto.getPassword())
            .dni(userDto.getDni())
            .birthday(userDto.getBirthday())
            .address(userDto.getAddress())
            .status(userDto.getStatus())
            .build();
    User savedUser = userRepository.save(newUser);

    return BankResponse.builder()
            .responseCode(Utils.USER_CREATION_SUCCESS_CODE)
            .responseMessage(Utils.USER_CREATION_SUCCESS_MESSAGE)
            .accountInfo(AccountInfo.builder()
                    .nameUser("Nombre: "+savedUser.getNameUser()+" Domicilio: "+savedUser.getAddress()+" D.N.I: "+savedUser.getDni()+" Birthday: "+savedUser.getBirthday())
                    .build())
            .build();
  }

  /**
   * Listamos usuarios
   */
  public List<User> findAll() {
    return userRepository.findAll();
  }



}
