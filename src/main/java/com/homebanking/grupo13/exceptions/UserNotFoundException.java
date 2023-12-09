package com.homebanking.grupo13.exceptions;

public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
