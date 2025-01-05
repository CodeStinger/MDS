package com.pcmc.mds.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcmc.mds.DTO.CustomerProfileDTO;
import com.pcmc.mds.Models.Customer;
import com.pcmc.mds.Models.CustomerProfile;
import com.pcmc.mds.Repositories.CustomerProfileRepository;
import com.pcmc.mds.Repositories.CustomerRepository;

@Service
public class CustomerProfileService {

    @Autowired
    private CustomerProfileRepository customerProfileRepository;
    
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Find a customer profile by customer ID.
     * @param customerId the ID of the customer
     * @return the CustomerProfile if found, or null otherwise
     */
    public CustomerProfile findByCustomerId(String customerId) {
        return customerProfileRepository.findByCustomerUniqueId(customerId);
    }

    /**
     * Check if a customer profile exists for a given customer ID.
     * @param customerId the ID of the customer
     * @return true if the profile exists, false otherwise
     */
    public boolean profileExistsByCustomerId(String customerId) {
        return customerProfileRepository.existsByCustomerUniqueId(customerId);
    }

    /**
     * Save or update a customer profile.
     * @param customerProfile the CustomerProfile to save or update
     * @return the saved or updated CustomerProfile
     */
    public CustomerProfile saveOrUpdateProfile(CustomerProfileDTO customerProfileDTO) {
    	String uniqueID = customerProfileDTO.getUniqueId();
    	CustomerProfile customerProfile = customerProfileDTO.mapDtoToEntity(customerProfileDTO);
    	Optional<CustomerProfile> existingProfile = Optional.of(customerProfileRepository.findByCustomerUniqueId(uniqueID));
    	if (existingProfile.isPresent()) {
    	    // If profile exists, set the profileId in the DTO
    	    customerProfile.setProfileId(existingProfile.get().getProfileId());
    	} 
    	Customer c = customerRepository.findByUniqueId(uniqueID);
//    	System.out.println(c.getUniqueId());
//    	c = customerRepository.findByUniqueId(c.getUniqueId());
    	customerProfile.setCustomer(c);
        return customerProfileRepository.save(customerProfile);
    }

    /**
     * Delete a customer profile by profile ID.
     * @param profileId the ID of the profile to delete
     */
    public void deleteProfileById(Long profileId) {
        customerProfileRepository.deleteById(profileId);
    }
}
