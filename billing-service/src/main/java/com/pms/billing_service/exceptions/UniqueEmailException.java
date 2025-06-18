package com.pms.billing_service.exceptions;

public class UniqueEmailException extends Exception{

    public UniqueEmailException(String exceptionMessage) {
        super(exceptionMessage);
    }

    public UniqueEmailException() {
        super("Email needs to be unique.");
    }
}
