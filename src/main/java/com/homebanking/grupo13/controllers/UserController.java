package com.homebanking.grupo13.controllers;

import com.homebanking.grupo13.entities.User;
import com.homebanking.grupo13.entities.dtos.BankResponse;
import com.homebanking.grupo13.entities.dtos.UserDto;
import com.homebanking.grupo13.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  public UserServiceImpl userServiceImpl;

  @PostMapping
  public BankResponse createUser(@RequestBody UserDto userDto){
    return userServiceImpl.createUser(userDto);
  }

  @GetMapping("/list")
  public List<User> list(){
    return userServiceImpl.findAll();
  }


}
