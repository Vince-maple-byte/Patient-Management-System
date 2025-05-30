package com.pms.patient.dto;

import com.pms.patient.model.Patient;
import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class PatientDTO {

    private String id;
    private String name;
    private String email;
    private String address;
    private String birthDate;


}
