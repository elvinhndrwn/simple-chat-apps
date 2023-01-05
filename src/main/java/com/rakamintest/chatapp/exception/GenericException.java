package com.rakamintest.chatapp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class GenericException extends RuntimeException{
    private final  HttpStatus httpStatus;
    private final String message;
}
