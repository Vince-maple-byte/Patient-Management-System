package com.pms.billing_service.service;

import com.pms.billing_service.dto.BillingCreation;
import com.pms.billing_service.dto.BillingMapper;
import com.pms.billing_service.dto.BillingResponse;
import com.pms.billing_service.model.Billing;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BillingMapperTests {

    private final BillingMapper billingMapper = new BillingMapper();

    @Test
    public void testBillingMapperToEntity_Success() {
        //Given
        String email = "jack@gmail.com";
        BillingCreation billingCreation = new BillingCreation(email, 0);
        Billing expectedBilling = Billing.builder()
                .email(email)
                .amountDue(0)
                .build();

        //When
        Billing billing = billingMapper.toEntity(billingCreation);

        //Then

        assertEquals(expectedBilling.getEmail(), billing.getEmail());
        assertEquals(expectedBilling.getAmountDue(), billing.getAmountDue());
    }

    @Test
    public void testBillingMapperToResponse_Success() {
        //Given
        String email = "jack@gmail.com";
        UUID id = UUID.randomUUID();
        BillingResponse billingResponse = new BillingResponse(id,email,0);
        Billing expectedBilling = Billing.builder()
                .email(email)
                .amountDue(0)
                .id(id)
                .build();

        //When
        BillingResponse result = billingMapper.toResponse(expectedBilling);

        //Then

        assertEquals(expectedBilling.getEmail(), billingResponse.getEmail());
        assertEquals(expectedBilling.getAmountDue(), billingResponse.getAmountDue());
        assertEquals(expectedBilling.getId(), billingResponse.getId());
    }
}
