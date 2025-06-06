package com.pms.patient.grpc;


import billing.BillingServiceGrpc;
import billing.BillingAccount;
import billing.Patient;
import org.springframework.context.annotation.Bean;
import org.springframework.grpc.client.GrpcChannelFactory;
import org.springframework.grpc.client.StubFactory;
import org.springframework.stereotype.Service;

@Service
public class GrpcClient {
    BillingServiceGrpc.BillingServiceBlockingStub stub;

    public GrpcClient(BillingServiceGrpc.BillingServiceBlockingStub stub) {
        this.stub = stub;
    }

    public BillingAccount createBillingAccount(String patientId, String email){
        billing.Patient patient = Patient
                .newBuilder()
                .setPatientId(patientId)
                .setEmail(email)
                .build();

        return stub.createBillingAccount(patient);
    }

    //public BillingAccount deleteBillingAccount(Str)
}
