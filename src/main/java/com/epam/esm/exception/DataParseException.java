package com.epam.esm.exception;

public class DataParseException extends RuntimeException {
    private Integer errorCode;

    public DataParseException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public DataParseException(Throwable cause, Integer errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }
}
