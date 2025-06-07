package com.pms.patient.config;

import billing.BillingServiceGrpc;
import io.grpc.*;
import io.grpc.stub.AbstractStub;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.BlockingStubFactory;
import org.springframework.grpc.client.GrpcChannelFactory;
import org.springframework.grpc.client.StubFactory;

import java.util.function.Supplier;

@Configuration
public class GrpcClientConfig {



    //I'll make some notes on stub factories here
    @Bean
    billing.BillingServiceGrpc.BillingServiceBlockingStub stub(GrpcChannelFactory channels) {
//        StubFactory<BillingServiceGrpc.BillingServiceBlockingStub> factory =
//                BillingServiceGrpc::newBlockingStub;

//        BillingServiceGrpc.BillingServiceBlockingStub stub =
//                channels.createStub("local", BillingServiceGrpc::newBlockingStub);


        return billing.BillingServiceGrpc.newBlockingStub(channels.createChannel("local"));
        //return BillingServiceGrpc.newStub(channels.createChannel("local"));
    }
}
