package com.scalable.service.delivery.service;


import com.scalable.service.delivery.exception.EntityAlreadyExistException;
import com.scalable.service.delivery.model.Customer;
import com.scalable.service.delivery.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer registerCustomer(Customer customer) {
        customerRepository.findByUsername(customer.getUsername()).ifPresent(existingCustomer -> {
            throw new EntityAlreadyExistException("Customer with username " + customer.getUsername() + " already exists");
        });
        return customerRepository.save(customer);
    }
}