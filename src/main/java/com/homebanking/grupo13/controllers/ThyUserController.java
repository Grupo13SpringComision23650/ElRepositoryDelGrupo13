package com.homebanking.grupo13.controllers;

import com.homebanking.grupo13.entities.dtos.BankResponse;
import com.homebanking.grupo13.entities.dtos.UserDto;
import com.homebanking.grupo13.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ThyUserController {

  @Autowired
  public UserServiceImpl userServiceImpl;


  // todo pagina inicio
  @GetMapping("/home")
  public String inicio(){
    return "home";
  }
  //todo nos lleva al formulario de creacion de usuarios
  @GetMapping("/registro")
  public String formNewUser(){
    return "registro";
  }

  //todo Lista usuario
  @GetMapping("/userlist")
  public String userlist(Model model){
    model.addAttribute("user", userServiceImpl.findAll());
    return "userlist";
  }

  //todo nos lleva al formulario de creacion de usuarios
  @PostMapping
  public BankResponse createUser(@RequestBody UserDto userDto){
    return userServiceImpl.createUser(userDto);
  }

  // todo generamos el metodo para agregar registro de usuario
  // todo Utilizamos la anotacion @PostMapping, ya que tomaremos los datos desde un formulario
  @PostMapping("/save")
  public String save(UserDto userDto){
    userServiceImpl.createUser(userDto);
    return "redirect:/userlist";
  }

}
