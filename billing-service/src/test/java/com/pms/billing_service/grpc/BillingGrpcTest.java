package com.pms.billing_service.grpc;

import billing.BillingAccount;
import billing.Patient;
import com.pms.billing_service.dto.BillingCreation;
import com.pms.billing_service.dto.BillingResponse;
import com.pms.billing_service.service.BillingService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BillingGrpcTest {

    private BillingService billingService;
    private BillingGrpc billingGrpc;

    @BeforeEach
    void setup() {
        billingService = mock(BillingService.class);
        billingGrpc = new BillingGrpc(billingService);
    }

    @Test
    void createBillingAccount_success() {
        // Given
        UUID billingId = UUID.randomUUID();
        String patientId = UUID.randomUUID().toString();
        String email = "test@example.com";

        BillingResponse billingResponse = BillingResponse.builder()
                .id(billingId)
                .email(email)
                .amountDue(0)
                .build();


        when(billingService.createBillingAccount(any(BillingCreation.class)))
                .thenReturn(billingResponse);

        Patient request = Patient.newBuilder()
                .setPatientId(patientId)
                .setEmail(email)
                .build();

        @SuppressWarnings("unchecked")
        StreamObserver<BillingAccount> responseObserver = mock(StreamObserver.class);

        // When
        billingGrpc.createBillingAccount(request, responseObserver);

        // Then
        ArgumentCaptor<BillingAccount> responseCaptor = ArgumentCaptor.forClass(BillingAccount.class);
        verify(responseObserver).onNext(responseCaptor.capture());
        verify(responseObserver).onCompleted();

        BillingAccount actual = responseCaptor.getValue();
        assertEquals(billingId.toString(), actual.getBillingAccountId());
        assertEquals(email, actual.getEmail());
        assertEquals(patientId, actual.getPatientId());
    }

    @Test
    void deleteBillingAccount_success() {
        // Given
        UUID billingId = UUID.randomUUID();
        UUID patientId = UUID.randomUUID();
        String email = "test@example.com";

        BillingResponse billingResponse = BillingResponse.builder()
                .id(billingId)
                .email(email)
                .amountDue(0)
                .build();

        when(billingService.deleteBillingAccount(eq(patientId)))
                .thenReturn(billingResponse);

        Patient request = Patient.newBuilder()
                .setPatientId(patientId.toString())
                .setEmail(email)
                .build();

        @SuppressWarnings("unchecked")
        StreamObserver<BillingAccount> responseObserver = mock(StreamObserver.class);

        // When
        billingGrpc.deleteBillingAccount(request, responseObserver);

        // Then
        ArgumentCaptor<BillingAccount> responseCaptor = ArgumentCaptor.forClass(BillingAccount.class);
        verify(responseObserver).onNext(responseCaptor.capture());
        verify(responseObserver).onCompleted();

        BillingAccount actual = responseCaptor.getValue();
        assertEquals(billingId.toString(), actual.getBillingAccountId());
        assertEquals(email, actual.getEmail());
        assertEquals(patientId.toString(), actual.getPatientId());
    }
}
