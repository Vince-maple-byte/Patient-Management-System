package com.pms.billing_service.repository;

import com.pms.billing_service.model.Billing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BillingRepository extends CrudRepository<Billing, UUID> {

    boolean existsByEmail(String email);

    Billing findByEmail(String email);
}
