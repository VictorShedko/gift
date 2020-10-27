package com.epam.esm.exception;

public class GiftException extends RuntimeException{
    private Integer errorCode;

    public GiftException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public GiftException(Throwable cause, Integer errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
