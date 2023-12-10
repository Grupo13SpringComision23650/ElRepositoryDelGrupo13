package com.homebanking.grupo13.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TransferNotFoundException extends ResponseStatusException {
    public TransferNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Transaccion no encontrada");
    }
}
