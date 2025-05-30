package com.pms.patient.repo;

import com.pms.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

//For JpaRepository<T,ID> T is the Domain class (@Entity) and ID is the primary key id variable in the Domain class
@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

    List<Patient> findByBirthDate(LocalDate birthDate);
    List<Patient> findByRegisteredDate(LocalDate registerdDate);
    boolean existsByEmail(String email);
    Patient findByEmail(String email);

}
