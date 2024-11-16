package com.scalable.service.delivery.repository;

import com.scalable.service.delivery.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindByUsername() {
        // Given
        Customer customer = new Customer();
        customer.setId("1");
        customer.setUsername("testuser");
        customerRepository.save(customer);

        // When
        Optional<Customer> foundCustomer = customerRepository.findByUsername("testuser");

        // Then
        assertThat(foundCustomer).isPresent();
        assertThat(foundCustomer.get().getUsername()).isEqualTo("testuser");
    }
}