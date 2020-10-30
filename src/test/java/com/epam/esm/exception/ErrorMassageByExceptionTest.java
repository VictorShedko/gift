package com.epam.esm.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class ErrorMassageByExceptionTest {

    @Test
    void GeneralException() {
        Exception exception=new Exception();
        JSONExceptionEntity expectedEntity=new JSONExceptionEntity(0,"", HttpStatus.INTERNAL_SERVER_ERROR);
        JSONExceptionEntity entity=ErrorMassageByException.createJSONExceptionEntity(exception);
        assertEquals(expectedEntity.getErrorCode(),entity.getErrorCode());
        assertEquals(expectedEntity.getStatus(),entity.getStatus());
    }

    @Test
    void RuntimeException() {
        Exception exception=new RuntimeException();
        JSONExceptionEntity expectedEntity=new JSONExceptionEntity(1,"", HttpStatus.INTERNAL_SERVER_ERROR);
        JSONExceptionEntity entity=ErrorMassageByException.createJSONExceptionEntity(exception);
        assertEquals(expectedEntity.getErrorCode(),entity.getErrorCode());
        assertEquals(expectedEntity.getStatus(),entity.getStatus());
    }

    @Test
    void GiftException() {
        Exception exception=new GiftException();
        JSONExceptionEntity expectedEntity=new JSONExceptionEntity(2,"", HttpStatus.I_AM_A_TEAPOT);
        JSONExceptionEntity entity=ErrorMassageByException.createJSONExceptionEntity(exception);
        assertEquals(expectedEntity.getErrorCode(),entity.getErrorCode());
        assertEquals(expectedEntity.getStatus(),entity.getStatus());
    }

    @Test
    void UniqFieldException() {
        Exception exception=new UniqFieldException("testField");
        JSONExceptionEntity expectedEntity=new JSONExceptionEntity(3,
                "insert error, violation of field uniqueness. Uniq fields:testField",
                HttpStatus.BAD_REQUEST);
        JSONExceptionEntity entity=ErrorMassageByException.createJSONExceptionEntity(exception);
        assertEquals(expectedEntity.getErrorCode(),entity.getErrorCode());
        assertEquals(expectedEntity.getStatus(),entity.getStatus());
        assertEquals(expectedEntity.getErrorMassage(),entity.getErrorMassage());
    }
}