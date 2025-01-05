package com.pcmc.mds.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcmc.mds.Models.Customer;
import com.pcmc.mds.Repositories.CustomerRepository;
import com.pcmc.mds.Util.CustomerUtil.UniqueIDGeneratorUtil;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UniqueIDGeneratorUtil uniqueIdGenerator;
    
    public Customer saveCustomer(Customer customer) {
    	customer.setUniqueId(uniqueIdGenerator.generateUniqueNumericId());
        return customerRepository.save(customer);
    }

    public Optional<Customer> findByUniqueId(String uniqueId) {
        return Optional.ofNullable(customerRepository.findByUniqueId(uniqueId));
    }

    public Optional<Customer> findByEmail(String email) {
        return Optional.ofNullable(customerRepository.findByEmail(email));
    }

    public boolean emailExists(String email) {
        return customerRepository.existsByEmail(email);
    }
    
    public Optional<Customer> findByPhone(String phone) {
        return Optional.ofNullable(customerRepository.findByPhone(phone));
    }

    public boolean phoneExists(String phone) {
//    	System.out.println(phone+ "   "+customerRepository.existsByPhone(phone));
        return customerRepository.existsByPhone(phone);
    }
}
