package com.homebanking.grupo13.controllers;

import com.homebanking.grupo13.entities.dtos.UserDto;
import com.homebanking.grupo13.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ThyUserController {

  @Autowired
  public UserService userService;


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
    model.addAttribute("user", userService.getUsers());
    return "userlist";
  }

  //todo nos lleva al formulario de creacion de usuarios
  @PostMapping
  public UserDto createUser(@RequestBody UserDto userDto){
    return userService.createUser(userDto);
  }

  // todo generamos el metodo para agregar registro de usuario
  // todo Utilizamos la anotacion @PostMapping, ya que tomaremos los datos desde un formulario
  @PostMapping("/save")
  public String save(UserDto userDto){
    userService.createUser(userDto);
    return "redirect:/userlist";
  }
}
