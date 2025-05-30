package com.pms.patient.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
public class PatientCreation {

    private String name;
    private String birthDate;
    private String email;
    private String address;


}
