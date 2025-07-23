package com.pms.billing_service.repository;

import com.pms.billing_service.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BillingRepository extends JpaRepository<Billing, UUID> {

    boolean existsByEmail(String email);

    Billing findByEmail(String email);
    Optional<Billing> findByPatientId(UUID patientId);
}
