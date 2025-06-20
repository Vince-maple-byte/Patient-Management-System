package com.pms.billing_service.grpc;

import billing.BillingAccount;
import billing.BillingServiceGrpc;
import billing.Patient;
import com.pms.billing_service.dto.BillingResponse;
import com.pms.billing_service.service.BillingService;
import io.grpc.Channel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.grpc.test.AutoConfigureInProcessTransport;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import io.grpc.ManagedChannel;
import io.grpc.inprocess.InProcessChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureInProcessTransport
class BillingGrpcIntegrationTests {

    private BillingServiceGrpc.BillingServiceBlockingStub stub;

    @Autowired
    void setStub(@Value("${spring.grpc.test.in-process-name}") String serverName) {
        ManagedChannel channel = InProcessChannelBuilder.forName(serverName).directExecutor().build();
        this.stub = BillingServiceGrpc.newBlockingStub(channel);
    }

    @MockitoBean
    private BillingService billingService;

    @Test
    void testCreateBillingAccount() {
        UUID patientId = UUID.randomUUID();
        UUID billingId = UUID.randomUUID();
        String email = "jack@example.com";

        when(billingService.createBillingAccount(any()))
                .thenReturn(BillingResponse.builder()
                        .id(billingId)
                        .email(email)
                        .amountDue(0)
                        .build());

        Patient request = Patient.newBuilder()
                .setPatientId(patientId.toString())
                .setEmail(email)
                .build();

        BillingAccount response = stub.createBillingAccount(request);

        assertEquals(billingId.toString(), response.getBillingAccountId());
        assertEquals(email, response.getEmail());
        assertEquals(patientId.toString(), response.getPatientId());
    }
}
