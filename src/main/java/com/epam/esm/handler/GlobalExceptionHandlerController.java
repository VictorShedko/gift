package com.epam.esm.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.epam.esm.exception.ErrorMassageByException;
import com.epam.esm.exception.JSONExceptionEntity;

@ControllerAdvice
public class GlobalExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleGiftException(Exception ce, WebRequest request) {
        JSONExceptionEntity entity = ErrorMassageByException.createJSONExceptionEntity(ce);
        return new ResponseEntity<>(entity, new HttpHeaders(), entity.getStatus());
    }

    @Override
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        JSONExceptionEntity entity = new JSONExceptionEntity(404, "miss", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(entity, headers, entity.getStatus());
    }

}
