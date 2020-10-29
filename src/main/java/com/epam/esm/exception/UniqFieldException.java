package com.epam.esm.exception;

public class UniqFieldException extends GiftException {
    private String uniqFields;

    public UniqFieldException(String uniqFields) {
        this.uniqFields = uniqFields;
    }

    public String getUniqFields() {
        return uniqFields;
    }


}
