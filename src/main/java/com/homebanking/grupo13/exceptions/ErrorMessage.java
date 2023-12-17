package com.homebanking.grupo13.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;



@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
    private int status;
    private LocalDateTime timestamp;
    private String message;
    private String description;
}