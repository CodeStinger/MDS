package com.pcmc.mds.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcmc.mds.Models.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Custom query method to find a customer by their unique ID
    Customer findByUniqueId(String uniqueId);

    // Custom query method to find a customer by their email
    Customer findByEmail(String email);

    // Check if a customer exists by email
    boolean existsByEmail(String email);
    
    //checks if uniquId already exists in the database
    boolean existsByUniqueId(String uniqueId);
    
    // Custom query method to find a customer by their phone number
    Customer findByPhone(String phone);

    // Check if a customer exists by phone number
    boolean existsByPhone(String phone);
}

