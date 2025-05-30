package com.pms.patient.controller;

import com.pms.patient.dto.PatientCreation;
import com.pms.patient.dto.PatientDTO;
import com.pms.patient.exceptions.EmailExistsException;
import com.pms.patient.exceptions.PatientNotFoundException;
import com.pms.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/")
    public ResponseEntity<List<PatientDTO>> getPatients() {
        List<PatientDTO> patientsDTO = patientService.getPatients();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(patientsDTO);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable("patientId") UUID patientId) throws PatientNotFoundException {
        PatientDTO patientDTO = patientService.getPatient(patientId);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(patientDTO);
    }

    @PostMapping("/")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientCreation patientCreation) throws EmailExistsException {
        PatientDTO patientDTO = patientService.createPatient(patientCreation);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(patientDTO);
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<PatientDTO> deletePatient(@PathVariable("patientId") UUID patientId) throws PatientNotFoundException {
        PatientDTO patientDTO = patientService.deletePatient(patientId);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(patientDTO);
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable("patientId") UUID patientId, @RequestBody PatientCreation patientCreation)
            throws PatientNotFoundException, EmailExistsException {
        PatientDTO patientDTO = patientService.updatePatient(patientId, patientCreation);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(patientDTO);
    }

}
