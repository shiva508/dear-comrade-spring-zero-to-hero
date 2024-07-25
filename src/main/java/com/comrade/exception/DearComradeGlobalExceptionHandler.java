package com.comrade.exception;

import com.comrade.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class DearComradeGlobalExceptionHandler {

    @ExceptionHandler(DearComradeException.class)
    public ResponseEntity<ErrorResponse> runtimeExceptionHandler(DearComradeException dearComradeException){
        return new ResponseEntity<>(new ErrorResponse(dearComradeException.getMessage(),408, new Date()), HttpStatus.BAD_REQUEST);
    }
}
