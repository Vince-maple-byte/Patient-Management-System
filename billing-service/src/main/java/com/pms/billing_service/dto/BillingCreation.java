package com.pms.billing_service.dto;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BillingCreation {
    private String email;
    private int amountDue;
    private UUID patientId;
}
