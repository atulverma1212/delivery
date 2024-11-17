package com.scalable.service.delivery.service;


import com.scalable.service.delivery.enums.ROLE;
import com.scalable.service.delivery.exception.EntityAlreadyExistException;
import com.scalable.service.delivery.model.Customer;
import com.scalable.service.delivery.model.User;
import com.scalable.service.delivery.repository.CustomerRepository;
import com.scalable.service.delivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserService userService;

    public Customer registerCustomer(Customer customer) {
        customerRepository.findByUsername(customer.getUsername()).ifPresent(existingCustomer -> {
            throw new EntityAlreadyExistException("Customer with username " + customer.getUsername() + " already exists");
        });

        User customerUser = User.builder().firstName(customer.getName())
                .username(customer.getUsername()).password(customer.getPassword())
                .roles(Set.of(ROLE.CUSTOMER.getRole())).build();
        userService.saveUser(customerUser);

        customer.setUserId(customerUser.getId());
        return customerRepository.save(customer);
    }
}