package com.pms.patient.exceptions;


public class EmailExistsException extends Exception{
    public EmailExistsException(String message) {
        super(message);
    }

    public EmailExistsException() {
        super("This email already exists.");
    }
}
