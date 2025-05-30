package com.pms.patient.dto;

import com.pms.patient.model.Patient;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PatientMapper {

    public PatientDTO toPatientDTO(Patient patient) {
        return PatientDTO.builder()
                .id(patient.getId().toString())
                .email(patient.getEmail())
                .address(patient.getAddress())
                .birthDate(patient.getBirthDate().toString())
                .build();
    }

    //DateTime is going to be formatted as this yyyy-MM-dd aka ISO-8601
    public Patient toEntity(PatientCreation patientCreation) {
        Patient patient = new Patient();

        patient.setName(patientCreation.getName());
        patient.setEmail(patientCreation.getEmail());
        patient.setAddress(patientCreation.getAddress());
        patient.setBirthDate(LocalDate.parse(patientCreation.getBirthDate()));
        patient.setRegisteredDate(LocalDate.now());

        return patient;

    }

}
