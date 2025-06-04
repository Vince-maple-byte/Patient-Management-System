package com.pms.billing_service.service;

import com.pms.billing_service.dto.BillingCreation;
import com.pms.billing_service.dto.BillingResponse;

import java.util.List;

public interface BillingService {

    public BillingResponse createBillingAccount(BillingCreation billingCreation);
    public List<BillingResponse> getBillingAccounts();
    //TODO:Make a method for the rest of the crud operation
}
