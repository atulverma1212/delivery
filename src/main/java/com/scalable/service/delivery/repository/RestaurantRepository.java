package com.scalable.service.delivery.repository;


import com.scalable.service.delivery.enums.MenuItemType;
import com.scalable.service.delivery.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {

    Optional<Restaurant> findByName(String name);

    @Query("{ 'menuItems': { $elemMatch: { 'type': ?0, 'name': { $regex: ?1, $options: 'i' } } } }")
    List<Restaurant> findByMenuItemTypeAndName(MenuItemType type, String name);
}