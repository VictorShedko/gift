package com.epam.esm.exception;

public class DataParseException extends GiftException {

    public DataParseException(String message, Integer errorCode) {
        super(message, errorCode);
    }

    public DataParseException(Throwable cause, Integer errorCode) {
        super(cause, errorCode);
    }
}
