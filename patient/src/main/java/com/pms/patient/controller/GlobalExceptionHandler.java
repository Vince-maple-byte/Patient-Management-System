package com.pms.patient.controller;

import com.pms.patient.exceptions.EmailExistsException;
import com.pms.patient.exceptions.PatientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<Object> handleEmailExistsException(EmailExistsException emailExistsException) {
        return new ResponseEntity<>(emailExistsException.getMessage(), HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Object> handlePatientNotFoundException(PatientNotFoundException patientNotFoundException) {
        return new ResponseEntity<>(patientNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
