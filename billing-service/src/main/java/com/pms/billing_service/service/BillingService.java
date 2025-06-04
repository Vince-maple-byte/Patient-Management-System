package com.pms.billing_service.service;

import com.pms.billing_service.dto.BillingCreation;
import com.pms.billing_service.dto.BillingResponse;

import java.util.List;
import java.util.UUID;

public interface BillingService {

    public BillingResponse createBillingAccount(BillingCreation billingCreation);
    public List<BillingResponse> getBillingAccounts();
    //TODO:Make a method for the rest of the crud operation
    public BillingResponse deleteBillingAccount(UUID patientId);
}
