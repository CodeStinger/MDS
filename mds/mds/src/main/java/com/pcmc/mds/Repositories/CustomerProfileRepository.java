package com.pcmc.mds.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcmc.mds.Models.CustomerProfile;

@Repository
public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {

    // Custom query method to find a profile by the associated customer ID
    CustomerProfile findByCustomerUniqueId(String Id);

    // Check if a profile exists by the associated customer ID
    boolean existsByCustomerUniqueId(String customerId);
    
    
    
}