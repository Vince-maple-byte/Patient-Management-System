package com.pms.patient.exceptions;

public class PatientNotFoundException extends Exception{
    public PatientNotFoundException() {
        super("Patient does not exist");
    }
}
