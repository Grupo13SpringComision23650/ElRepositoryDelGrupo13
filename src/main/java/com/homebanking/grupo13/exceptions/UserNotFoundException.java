package com.homebanking.grupo13.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;



public class UserNotFoundException extends ResponseStatusException {
    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Usuario no encontrado");
    }
}
