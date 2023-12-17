package com.homebanking.grupo13.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class ErrorMessage {
    private int status;
    private LocalDateTime timestamp;
    private String message;

    private String description;

    public ErrorMessage(int status, LocalDateTime timestamp, String message, String description) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

