package com.pcmc.mds.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.pcmc.mds.DTO.CustomerDTO;
import com.pcmc.mds.Models.Customer;
import com.pcmc.mds.Models.OtpDetails;
import com.pcmc.mds.Services.CustomerService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    
    private final Map<String, OtpDetails> otpStorage = new HashMap<>();

    // Create a new customer
    
    public ResponseEntity<String> createCustomer(CustomerDTO customerDTO) {
        if ((customerDTO.getEmail() != null && !customerDTO.getEmail().equals("")) && customerService.emailExists(customerDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists.");
        }
        if ((customerDTO.getPhone()!= null && !customerDTO.getPhone().equals("")) && customerService.phoneExists(customerDTO.getPhone())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Phone already exists.");
        }

        Customer customer = customerDTO.toEntity();
        Customer savedCustomer = customerService.saveCustomer(customer);

        otpStorage.remove(customerDTO.getEmail());
        otpStorage.remove(customerDTO.getPhone());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(
            "Customer created successfully with unique ID: " + savedCustomer.getUniqueId());
    }

    
    @GetMapping("/{uniqueId}")
    public ResponseEntity<CustomerDTO> getCustomerByUniqueId(@PathVariable String uniqueId) {
        Optional<Customer> customer = customerService.findByUniqueId(uniqueId);
        return customer
                .map(value -> ResponseEntity.ok(CustomerDTO.fromEntity(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
   
    // Fetch a customer by email
    @GetMapping("/email")
    public ResponseEntity<?> getCustomerByEmail(@RequestParam String email) {
        Optional<Customer> customer = customerService.findByEmail(email);
        if (customer.isPresent()) {
            // If customer is found, return a DTO
            CustomerDTO customerDTO = CustomerDTO.fromEntity(customer.get());
            return ResponseEntity.ok(customerDTO);
        } else {
            // If customer is not found, return an error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email does not exist");
        }
    }

    // Check if email exists
    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkEmailExists(@RequestParam String email) {
        return ResponseEntity.ok(customerService.emailExists(email));
    }
    
    @PostMapping("/generate-otp-email")
    public ResponseEntity<String> generateOtpEmail(@RequestParam String email) {
        if (customerService.emailExists(email)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" Account with this email already exists");
        }

    	
        String otp = String.valueOf((int) (Math.random() * 900000) + 100000); // 6-digit OTP
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);
        otpStorage.put(email, new OtpDetails(otp, expiryTime));

        // Simulate sending OTP
        System.out.println("Generated OTP for " + email + ": " + otp);

        return ResponseEntity.ok("OTP sent successfully to " + email);
    }
    
    @PostMapping("/generate-otp-phone")
    public ResponseEntity<String> generateOtpPhone(@RequestParam String phone) {
    	phone="+"+phone.trim();
        if (customerService.phoneExists(phone)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" Account with this phone already exists");
        }

    	
        String otp = String.valueOf((int) (Math.random() * 900000) + 100000); // 6-digit OTP
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);
        otpStorage.put(phone, new OtpDetails(otp, expiryTime));

        // Simulate sending OTP
        System.out.println("Generated OTP for " + phone + ": " + otp);

        return ResponseEntity.ok("OTP sent successfully to " + phone);
    }

    
    @PostMapping("/validate-otp-email")
    public ResponseEntity<String> validateOtpEmail(@RequestParam String email, @RequestParam String otp) {
        OtpDetails otpDetails = otpStorage.get(email);

        if (otpDetails == null || LocalDateTime.now().isAfter(otpDetails.getExpiryTime())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OTP has expired or not found.");
        }

        if (!otpDetails.getOtp().equals(otp)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP.");
        }

        otpStorage.remove(email);
        otpStorage.put(email,otpDetails);
        return ResponseEntity.ok("OTP validation successful.");
    }
    
    @PostMapping("/validate-otp-phone")
    public ResponseEntity<String> validateOtpPhone(@RequestParam String phone, @RequestParam String otp) {
        OtpDetails otpDetails = otpStorage.get("+"+phone.trim());
        System.out.println("+"+phone);
        System.out.println(otpStorage);
        if (otpDetails == null || LocalDateTime.now().isAfter(otpDetails.getExpiryTime())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OTP has expired or not found.");
        }

        if (!otpDetails.getOtp().equals(otp)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP.");
        }

        otpStorage.remove(phone);
        otpStorage.put(phone,otpDetails);
        return ResponseEntity.ok("OTP validation successful.");
    }
    
    @PostMapping("/set-password")
    public ResponseEntity<String> setPassword(@Validated @RequestBody CustomerDTO customerDTO) {
//    	System.out.println(otpStorage);
        
        if ((customerDTO.getEmail()!= null && !customerDTO.getEmail().equals("")) ) {
        	if(!otpStorage.containsKey(customerDTO.getEmail()))
        		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OTP validation is required before setting a password.");
        }
        if ((customerDTO.getPhone()!= null && !customerDTO.getPhone().equals(""))) {
        	if(!otpStorage.containsKey(customerDTO.getPhone()))
        		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OTP validation is required before setting a password.");
        }
        
       return createCustomer(customerDTO);
    }
}
