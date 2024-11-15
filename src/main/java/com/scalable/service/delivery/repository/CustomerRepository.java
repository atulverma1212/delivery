package com.scalable.service.delivery.repository;


import com.scalable.service.delivery.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
