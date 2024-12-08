package com.comrade.config.exception;

import com.comrade.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class DearComradeExceptionHandler {

    @ExceptionHandler({AzureException.class})
    public ResponseEntity<ExceptionResponse> azureFailureHandler(AzureException azureException){
        ExceptionResponse exceptionResponse= ExceptionResponse.builder().message(azureException.getMessage())
                .statusCode(400)
                .timeStamp(new Date())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
