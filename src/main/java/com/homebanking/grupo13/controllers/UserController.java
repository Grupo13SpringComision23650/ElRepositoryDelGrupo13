package com.homebanking.grupo13.controllers;

import com.homebanking.grupo13.entities.User;
import com.homebanking.grupo13.entities.dtos.UserDto;
import com.homebanking.grupo13.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService service;

    public UserController(UserService service){
        this.service = service;
    }


    // Obtener una lista de usuarios registrados

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getUsers());
    }

    // Obtener la info de un solo usuario


    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getUserById(id));
    }

    // Crear/Registrar un usuario

    @PostMapping(value = "/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(user));
    }

    // Modificar TOTALMENTE un usuario (PUT)
    @PutMapping(value="/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto user){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateUser(id, user));
    }



    // Modificar PARCIALMENTE un usuario (PATCH)
    @PatchMapping(value = "/{id}")
    public String updateParcialUser(){
        return "";
    }

    // Eliminar un usuario

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteUser(id));
    }
}
