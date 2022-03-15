package com.chseidler.invoicesappbe.exception;

import com.chseidler.invoicesappbe.model.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = { UserServiceException.class})
    public ResponseEntity<Object> handleUserServiceException(UserServiceException exception, WebRequest request) {

        System.out.println("------Inside handleUserServiceException--------");
        exception.printStackTrace();

        ErrorMessage errorMessage = new ErrorMessage(201, exception.toString().replaceAll(".+: ", ""), new Date());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleAnyException(Exception exception, WebRequest request) {

        System.out.println("------Inside handleAnyException--------");
        exception.printStackTrace();

        ErrorMessage errorMessage = new ErrorMessage(301, exception.toString().replaceAll(".+: ", ""), new Date());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
