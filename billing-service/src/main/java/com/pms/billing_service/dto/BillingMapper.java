package com.pms.billing_service.dto;

import com.pms.billing_service.model.Billing;
import org.springframework.stereotype.Component;

@Component
public class BillingMapper {

    public Billing toEntity(BillingCreation billingCreation) {
        Billing billing = new Billing();
        billing.setEmail(billingCreation.getEmail());
        billing.setAmountDue(billingCreation.getAmountDue());
        billing.setPatientId(billingCreation.getPatientId());
        return billing;
    }

    public BillingResponse toResponse(Billing billing) {
        BillingResponse billingResponse = new BillingResponse();

        billingResponse.setEmail(billing.getEmail());
        billingResponse.setId(billing.getId());
        billingResponse.setAmountDue(billing.getAmountDue());
        billingResponse.setPatientId(billing.getPatientId());

        return billingResponse;
    }
}
