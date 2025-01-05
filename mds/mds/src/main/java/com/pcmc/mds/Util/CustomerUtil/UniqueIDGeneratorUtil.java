package com.pcmc.mds.Util.CustomerUtil;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcmc.mds.Repositories.CustomerRepository;

@Component
public class UniqueIDGeneratorUtil {

	 	@Autowired
	    private CustomerRepository customerRepository;

	    public String generateUniqueNumericId() {
	        Random random = new Random();
	        String uniqueId;

	        // Retry mechanism to ensure uniqueness
	        do {
	            uniqueId = String.valueOf(10000000 + random.nextInt(90000000)); // Generate 8-digit number
	        } while (customerRepository.existsByUniqueId(uniqueId)); // Regenerate if it exists

	        return uniqueId;
	    }
}
