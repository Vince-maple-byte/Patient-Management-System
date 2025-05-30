package com.pms.patient.service;

import com.pms.patient.dto.PatientCreation;
import com.pms.patient.dto.PatientDTO;
import com.pms.patient.dto.PatientMapper;
import com.pms.patient.exceptions.EmailExistsException;
import com.pms.patient.exceptions.PatientNotFoundException;
import com.pms.patient.model.Patient;
import com.pms.patient.repo.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService{
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public List<PatientDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();

        return patients.stream().map(patient -> patientMapper.toPatientDTO(patient)).toList();
    }

    @Override
    public PatientDTO getPatient(UUID id) throws PatientNotFoundException{
        Optional<Patient> patient = patientRepository.findById(id);
        if(patient.isEmpty()) throw new PatientNotFoundException();
        return patientMapper.toPatientDTO(patient.get());
    }

    public PatientDTO createPatient(PatientCreation patientCreation) throws EmailExistsException {
        Patient p = patientMapper.toEntity(patientCreation);

        if(patientRepository.existsByEmail(p.getEmail())) {
            throw new EmailExistsException();
        } else{
            patientRepository.save(p);
            return patientMapper.toPatientDTO(p);
        }
    }

    @Override
    public PatientDTO updatePatient(UUID id, PatientCreation patientCreation) throws PatientNotFoundException, EmailExistsException {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if(patientOptional.isEmpty()) {
            throw new PatientNotFoundException();
        }
        //We want to check if the email they provided in patientCreation does not exist in another entity (It doesn't matter if the email is the same as the
        //previous one
        else if(patientRepository.existsByEmail(patientCreation.getEmail()) &&
                patientRepository.findByEmail(patientCreation.getEmail()).getId() != patientOptional.get().getId()){

            throw new EmailExistsException();

        }
        else {
            Patient p = patientOptional.get();

            p.setName(patientCreation.getName());
            p.setAddress(patientCreation.getAddress());
            p.setBirthDate(LocalDate.parse(patientCreation.getBirthDate()));
            p.setEmail(patientCreation.getEmail());

            patientRepository.save(p);
            return patientMapper.toPatientDTO(p);
        }

    }

    @Override
    public PatientDTO deletePatient(UUID id) throws PatientNotFoundException{
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if(patientOptional.isEmpty()) {
            throw new PatientNotFoundException();
        }
        //We want to check if the email they provided in patientCreation does not exist in another entity (It doesn't matter if the email is the same as the
        //previous one

        else {
            Patient p = patientOptional.get();

            patientRepository.delete(p);

            patientRepository.save(p);
            return patientMapper.toPatientDTO(p);
        }
    }

}
