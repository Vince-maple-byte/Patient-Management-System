package com.pms.billing_service.service;

import com.pms.billing_service.dto.BillingCreation;
import com.pms.billing_service.dto.BillingMapper;
import com.pms.billing_service.dto.BillingResponse;
import com.pms.billing_service.model.Billing;
import com.pms.billing_service.repository.BillingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class BillingServiceImpl implements BillingService{

    //TODO Write a exception for when the same email is entered

    private final BillingRepository billingRepository;
    private final BillingMapper billingMapper;

    public BillingServiceImpl(BillingRepository billingRepository, BillingMapper billingMapper) {
        this.billingRepository = billingRepository;
        this.billingMapper = billingMapper;
    }

    @Override
    public BillingResponse createBillingAccount(BillingCreation billingCreation) {
        Billing billing = billingMapper.toEntity(billingCreation);
        billingRepository.save(billing);
        return new BillingResponse(billing.getId(),billing.getEmail(),billing.getAmountDue(),billing.getPatientId());
    }

    @Override
    public List<BillingResponse> getBillingAccounts() {
        List<Billing> billings = billingRepository.findAll();
        return billings.stream().map(billingMapper::toResponse).toList();
    }

    @Override
    public BillingResponse deleteBillingAccount(UUID patientId) {
        Optional<Billing> deletedBilling = billingRepository.findByPatientId(patientId);
        deletedBilling.ifPresent(billingRepository::delete);
        return deletedBilling.map(billingMapper::toResponse).orElse(null);

    }
}
