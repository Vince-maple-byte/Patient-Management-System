package com.pms.billing_service.service;

import com.pms.billing_service.config.ServiceConfig;
import com.pms.billing_service.dto.BillingCreation;
import com.pms.billing_service.dto.BillingResponse;
import com.pms.billing_service.repository.BillingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(ServiceConfig.class) //We use the @import to tell Springboot to use the TestConfig file
public class BillingServiceIntegrationTests {

    @Autowired
    private BillingService billingService;

    @Autowired
    private BillingRepository billingRepository;

    //After each unit test delete all the entities in the database
    @AfterEach
    void tearDown(){
        billingRepository.deleteAll();

    }


    @Test
    void testBillingServiceCreateBillingAccount_Success() {
        //Given
        String email = "jack@gmail.com";
        int amountDue = 0;
        UUID patientId = UUID.randomUUID();
        BillingCreation billingCreation = new BillingCreation(email, amountDue, patientId);

        //When
        BillingResponse billingResponse = billingService.createBillingAccount(billingCreation);

        //Then
        assertEquals(email, billingResponse.getEmail());
        assertEquals(amountDue, billingResponse.getAmountDue());
        assertEquals(patientId.toString(), billingResponse.getPatientId().toString());
        assertInstanceOf(UUID.class, billingResponse.getId()); //Don't pass in the class just the object in the actual

    }

    @Test
    void testBillingServiceGetBillingAccounts_Success(){
        //Given

        String email = "jack@gmail.com";
        String email2 = "jeff@gmail.com";
        String email3 = "john@gmail.com";
        UUID patientId = UUID.randomUUID();
        UUID patientId2 = UUID.randomUUID();
        UUID patientId3 = UUID.randomUUID();
        int amountDue = 0;
        BillingCreation billingCreation = new BillingCreation(email, amountDue, patientId);
        BillingCreation billingCreation2 = new BillingCreation(email2, amountDue, patientId2);
        BillingCreation billingCreation3 = new BillingCreation(email3, amountDue, patientId3);

        //When
        billingService.createBillingAccount(billingCreation);
        billingService.createBillingAccount(billingCreation2);
        billingService.createBillingAccount(billingCreation3);
        List<BillingResponse> results = billingService.getBillingAccounts();


        //Then
        assertEquals(3, results.size());
        //For each of the elements
        assertEquals(email, results.get(0).getEmail());
        assertEquals(amountDue, results.get(0).getAmountDue());
        assertEquals(patientId.toString(), results.get(0).getPatientId().toString());
        assertInstanceOf(UUID.class, results.get(0).getId()); //Don't pass in the class just the object in the actual

        //Element 2
        assertEquals(email2, results.get(1).getEmail());
        assertEquals(amountDue, results.get(1).getAmountDue());
        assertEquals(patientId2.toString(), results.get(1).getPatientId().toString());
        assertInstanceOf(UUID.class, results.get(1).getId()); //Don't pass in the class just the object in the actual

        //For each of the elements
        assertEquals(email3, results.get(2).getEmail());
        assertEquals(amountDue, results.get(2).getAmountDue());
        assertEquals(patientId3.toString(), results.get(2).getPatientId().toString());
        assertInstanceOf(UUID.class, results.get(2).getId()); //Don't pass in the class just the object in the actual
    }

    @Test
    void testBillingServiceDeleteBillingAccount_Success() {
        //Given
        String email = "jack@gmail.com";
        UUID patientId = UUID.randomUUID();
        int amountDue = 0;
        BillingCreation billingCreation = new BillingCreation(email, amountDue, patientId);

        //When
        BillingResponse billingResponse = billingService.createBillingAccount(billingCreation);
        BillingResponse deletedResponse = billingService.deleteBillingAccount(patientId);
        List<BillingResponse> results = billingService.getBillingAccounts();

        //Then
        assertTrue(results.isEmpty());

        assertEquals(billingResponse.getEmail(), deletedResponse.getEmail());
        assertEquals(billingResponse.getAmountDue(), deletedResponse.getAmountDue());
        assertEquals(billingResponse.getPatientId(), deletedResponse.getPatientId());

        assertInstanceOf(UUID.class, deletedResponse.getId()); //Don't pass in the class just the object in the actual
    }



}
