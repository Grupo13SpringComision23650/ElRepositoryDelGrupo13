package com.homebanking.grupo13.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/* Bueno ya arregle excepciones gracias a este señor, que dios se lo page....
https://www.bezkoder.com/spring-boot-restcontrolleradvice/
*/
@RestControllerAdvice
public class RestControllerHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(
            RecordNotFoundException ex, WebRequest request
    ) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return message;
    }

    @ExceptionHandler(RecordAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorMessage resourceNotFoundException(
            RecordAlreadyExistsException ex, WebRequest request
    ) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return message;
    }

    @ExceptionHandler(InvalidStatusException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorMessage resourceNotFoundException(
            InvalidStatusException ex, WebRequest request
    ) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return message;
    }

}
