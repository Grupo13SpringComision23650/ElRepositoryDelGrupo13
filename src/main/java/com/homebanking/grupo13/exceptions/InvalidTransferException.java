package com.homebanking.grupo13.exceptions;

public class InvalidTransferException extends RuntimeException {
        public InvalidTransferException(String message) {
            super(message);
        }
    }
