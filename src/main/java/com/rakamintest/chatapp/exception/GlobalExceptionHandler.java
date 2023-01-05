package com.rakamintest.chatapp.exception;

import com.rakamintest.chatapp.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<Object> buildResponseEntity(String errorMessage, HttpStatus httpStatus) {
        ErrorResponseDto error = ErrorResponseDto.builder()
                .errorMessage(errorMessage)
                .httpStatus(httpStatus.getReasonPhrase())
                .httpStatusCode(httpStatus.value())
                .build();
        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler(value = GenericException.class)
    public ResponseEntity<Object> exception(GenericException exception){
        return buildResponseEntity(exception.getMessage(), exception.getHttpStatus());
    }
}
