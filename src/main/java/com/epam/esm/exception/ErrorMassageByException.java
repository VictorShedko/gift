package com.epam.esm.exception;

import java.util.function.Function;

import org.springframework.http.HttpStatus;

public enum ErrorMassageByException {
    GENERAL(0, Exception.class, ex -> {
        return "Unknown exception";
    }, HttpStatus.INTERNAL_SERVER_ERROR), GENERAL_RUNTIME(1, RuntimeException.class, ex -> {
        return "Unknown runtime exception";
    }, HttpStatus.INTERNAL_SERVER_ERROR), GENERAL_APP(2, GiftException.class, ex -> {
        return "Unknown gift exception";
    }, HttpStatus.I_AM_A_TEAPOT), UNIQ_FIELD(3, UniqFieldException.class, ex -> {
        return "insert error, violation of field uniqueness. Uniq fields:" + ((UniqFieldException) ex).getUniqFields();
    }, HttpStatus.BAD_REQUEST), RESOURCE_NOT_FOUND(4, ResourceNotFoundedException.class, ex -> {
        return "Resource not founded requested resource is " + ((ResourceNotFoundedException) ex).getResourceName()
                + " requested value is " + ((ResourceNotFoundedException) ex).getRequestedValues();
    }, HttpStatus.NOT_FOUND), ID_IN_INSERT(5, IdInInsertException.class, ex -> {
        return "Id in inserted entity is forbidden";
    }, HttpStatus.NOT_FOUND);

    private int code;
    private Class<? extends Exception> exceptionClass;
    private Function<Exception, String> messageBuilder;
    private HttpStatus status;

    ErrorMassageByException(int i, Class<? extends Exception> exceptionClass,
            Function<Exception, String> messageBuilder, HttpStatus status) {
        this.code = i;
        this.exceptionClass = exceptionClass;
        this.messageBuilder = messageBuilder;
        this.status = status;
    }

    public Class<? extends Exception> getExceptionClass() {
        return exceptionClass;
    }

    public static JSONExceptionEntity createJSONExceptionEntity(Exception exception) {
        for (var errorMessage : ErrorMassageByException.values()) {
            if (errorMessage.getExceptionClass() == exception.getClass()) {
                return new JSONExceptionEntity(errorMessage.code, errorMessage.messageBuilder.apply(exception),
                        errorMessage.status);
            }
        }
        if (exception instanceof RuntimeException) {
            return new JSONExceptionEntity(GENERAL_RUNTIME.code, GENERAL_RUNTIME.messageBuilder.apply(exception),
                    GENERAL_RUNTIME.status);
        }
        return new JSONExceptionEntity(GENERAL.code, GENERAL.messageBuilder.apply(exception), GENERAL.status);

    }

}
