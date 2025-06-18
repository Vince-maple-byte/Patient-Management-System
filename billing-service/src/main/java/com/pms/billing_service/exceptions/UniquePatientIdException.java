package com.pms.billing_service.exceptions;

import java.util.UUID;

public class UniquePatientIdException extends Exception{
    public UniquePatientIdException(String message) {
        super(message);
    }

    public UniquePatientIdException(UUID patientId) {
        super(String.format("Patient ID: %s needs to be unique", patientId.toString()));
    }

    public UniquePatientIdException() {
        super("Patient ID needs to be unique");
    }
}
