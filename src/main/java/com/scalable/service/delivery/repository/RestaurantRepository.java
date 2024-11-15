package com.scalable.service.delivery.repository;


import com.scalable.service.delivery.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
}