package com.scalable.service.delivery.repository;

import com.scalable.service.delivery.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    @Query(value = "{ 'customerId': ?0 }", sort = "{ 'createdDate': -1 }")
    List<Order> findByCustomerIdOrderByCreatedAtDesc(String customerId);
}