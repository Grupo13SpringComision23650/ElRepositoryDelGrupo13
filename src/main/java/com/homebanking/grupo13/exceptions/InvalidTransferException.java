package com.homebanking.grupo13.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidTransferException extends ResponseStatusException {
    public InvalidTransferException(String message) {
        super(HttpStatus.BAD_REQUEST,message);
    }
    public InvalidTransferException() {
        super(HttpStatus.BAD_REQUEST, "Transaccion no valida");
    }
}
