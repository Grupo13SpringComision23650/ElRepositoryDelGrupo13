package com.homebanking.grupo13.services;

import com.homebanking.grupo13.entities.User;
import com.homebanking.grupo13.entities.dtos.BankResponse;
import com.homebanking.grupo13.entities.dtos.UserDto;

import java.util.List;

public interface IUserService{

  BankResponse createUser(UserDto userDto);

  List<User> findAll();
}
