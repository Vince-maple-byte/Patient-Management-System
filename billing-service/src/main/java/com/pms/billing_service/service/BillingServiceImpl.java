package com.pms.billing_service.service;

import com.pms.billing_service.dto.BillingCreation;
import com.pms.billing_service.dto.BillingResponse;
import com.pms.billing_service.model.Billing;
import com.pms.billing_service.repository.BillingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingServiceImpl implements BillingService{

    private final BillingRepository billingRepository;

    public BillingServiceImpl(BillingRepository billingRepository) {
        this.billingRepository = billingRepository;
    }

    //TODO: Make a mapper class to convert BillingCreation object to Billing entity object
    @Override
    public BillingResponse createBillingAccount(BillingCreation billingCreation) {
        Billing billing = new Billing();
        billing.setEmail(billingCreation.getEmail());
        billing.setAmountDue(billingCreation.getAmountDue());
        billingRepository.save(billing);
        return new BillingResponse(billing.getId(),billing.getEmail());
    }

    @Override
    public List<BillingResponse> getBillingAccounts() {
        return null;
    }
}
