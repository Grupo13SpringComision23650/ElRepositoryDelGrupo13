package com.homebanking.grupo13.exceptions;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(String message) {
        super(message);
    }
}
