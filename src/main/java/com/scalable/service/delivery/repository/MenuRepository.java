package com.scalable.service.delivery.repository;

import com.scalable.service.delivery.model.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MenuRepository extends MongoRepository<Menu, String> {
    Menu findByRestaurantId(String restaurantId);
}