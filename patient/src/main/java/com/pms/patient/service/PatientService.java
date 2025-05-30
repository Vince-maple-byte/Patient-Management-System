package com.pms.patient.service;

import com.pms.patient.dto.PatientCreation;
import com.pms.patient.dto.PatientDTO;
import com.pms.patient.exceptions.EmailExistsException;
import com.pms.patient.exceptions.PatientNotFoundException;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    public List<PatientDTO> getPatients();
    public PatientDTO getPatient(UUID id) throws PatientNotFoundException;
    public PatientDTO createPatient(PatientCreation patientCreation) throws EmailExistsException;
    public PatientDTO deletePatient(UUID id) throws PatientNotFoundException;
    public PatientDTO updatePatient(UUID id, PatientCreation patientCreation) throws PatientNotFoundException, EmailExistsException;
}
