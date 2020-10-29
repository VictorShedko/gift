package com.epam.esm.handler;

import com.epam.esm.exception.GiftException;
import com.epam.esm.exception.JSONExceptionEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandlerController {
    @ExceptionHandler({GiftException.class})
    public ResponseEntity<Object> handleCustomException(GiftException ce, WebRequest request) {
        return new ResponseEntity<>(
                new JSONExceptionEntity(ce, HttpStatus.I_AM_A_TEAPOT), new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception ce, WebRequest request) {
        return new ResponseEntity<>(
                new JSONExceptionEntity(ce, HttpStatus.I_AM_A_TEAPOT), new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT);
    }


}
