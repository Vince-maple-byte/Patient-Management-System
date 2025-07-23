package com.pms.billing_service.config;

import com.pms.billing_service.dto.BillingMapper;
import com.pms.billing_service.repository.BillingRepository;
import com.pms.billing_service.service.BillingService;
import com.pms.billing_service.service.BillingServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

//We use this to create the config file to create any beans that we would use in some of the other tests
//This is good if you want to perform integration tests and keep the same context inbetween tests
@TestConfiguration
public class ServiceConfig {

    private final BillingRepository billingRepository;

    public ServiceConfig(BillingRepository billingRepository) {
        this.billingRepository = billingRepository;
    }

    @Bean
    public BillingService billingService() {
        return new BillingServiceImpl(billingRepository, new BillingMapper());
    }
}
