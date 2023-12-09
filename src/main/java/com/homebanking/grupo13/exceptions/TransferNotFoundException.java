package com.homebanking.grupo13.exceptions;

public class TransferNotFoundException extends RuntimeException {
        public TransferNotFoundException(String message) {
            super(message);
        }
    }
