package com.pms.billing_service.grpc;

//import org.springframework.grpc.server.service.GrpcService;f
import billing.BillingAccount;
import billing.BillingServiceGrpc;
import com.pms.billing_service.service.BillingService;
import billing.Patient;
import com.pms.billing_service.dto.BillingCreation;
import com.pms.billing_service.dto.BillingResponse;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

import java.util.UUID;


@GrpcService
public class BillingGrpc extends BillingServiceGrpc.BillingServiceImplBase {

    private final BillingService billingService;

    public BillingGrpc(BillingService billingService) {
        this.billingService = billingService;
    }

    @Override
    public void createBillingAccount(Patient request, StreamObserver<BillingAccount> responseObserver) {
        BillingCreation billingCreation = new BillingCreation(
                request.getEmail(),
                0,
                UUID.fromString(request.getPatientId()
                ));

        BillingResponse billingResponse = billingService.createBillingAccount(billingCreation);

        BillingAccount billingAccount = BillingAccount
                .newBuilder()
                .setBillingAccountId(billingResponse.getId().toString())
                .setEmail(billingCreation.getEmail())
                .setPatientId(request.getPatientId())
                .build();

        responseObserver.onNext(billingAccount);
        responseObserver.onCompleted();

    }


    public void deleteBillingAccount(billing.Patient request, io.grpc.stub.StreamObserver<billing.BillingAccount> responseObserver){

        BillingResponse billingResponse = billingService.deleteBillingAccount(UUID.fromString(request.getPatientId()));

        BillingAccount billingAccount = BillingAccount
                .newBuilder()
                .setBillingAccountId(billingResponse.getId().toString())
                .setEmail(request.getEmail())
                .setPatientId(request.getPatientId())
                .build();

        responseObserver.onNext(billingAccount);
        responseObserver.onCompleted();
    }


}
