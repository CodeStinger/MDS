package com.pcmc.mds.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pcmc.mds.DTO.CustomerProfileDTO;
import com.pcmc.mds.Models.CustomerProfile;
import com.pcmc.mds.Services.CustomerProfileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customer-profile")
public class CustomerProfileController {

    @Autowired
    private CustomerProfileService customerProfileService;

    /**
     * Get a customer profile by customer ID.
     * @param customerId the ID of the customer
     * @return the CustomerProfile if found, or a NOT_FOUND response
     */
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerProfile> getProfileByCustomerId(@PathVariable String customerId) {
        CustomerProfile profile = customerProfileService.findByCustomerId(customerId);
        if (profile != null) {
            return ResponseEntity.ok(profile);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    /**
     * Create or update a customer profile.
     * @param customerProfileDTO the profile details in DTO format
     * @return the saved or updated CustomerProfile
     */
    @PostMapping
    public ResponseEntity<CustomerProfile> createOrUpdateProfile(
            @Valid @RequestBody CustomerProfileDTO customerProfileDTO) {
    	System.out.println(customerProfileDTO.getUniqueId()+" this is first occurence");
        CustomerProfile savedProfile = customerProfileService.saveOrUpdateProfile(customerProfileDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfile);
    }

    /**
     * Delete a customer profile by profile ID.
     * @param profileId the ID of the profile to delete
     * @return a NO_CONTENT response if successful
     */
    @DeleteMapping("/{profileId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long profileId) {
        customerProfileService.deleteProfileById(profileId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Check if a profile exists for a customer ID.
     * @param customerId the ID of the customer
     * @return true if the profile exists, false otherwise
     */
    @GetMapping("/exists/{customerId}")
    public ResponseEntity<Boolean> profileExists(@PathVariable String customerId) {
        boolean exists = customerProfileService.profileExistsByCustomerId(customerId);
        return ResponseEntity.ok(exists);
    }


}
