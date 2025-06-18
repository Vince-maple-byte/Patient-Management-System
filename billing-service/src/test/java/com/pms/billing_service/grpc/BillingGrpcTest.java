package com.pms.billing_service.grpc;

import com.pms.billing_service.model.Billing;
import com.pms.billing_service.service.BillingService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.UUID;

public class BillingGrpcTest {
    //TODO: Finish with the unit tests
    @Mock
    BillingService billingService;

    @InjectMocks
    BillingGrpc billingGrpc;

    @Test
    void testCreatingBillingAccount_Success(){
        //Given
        Billing billing = new Billing(UUID.randomUUID(), "jack@gmail.com", 50);
        Mockito.when(billingService.createBillingAccount());
    }
}
