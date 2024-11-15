package com.scalable.service.delivery.service;


import com.scalable.service.delivery.enums.MenuItemType;
import com.scalable.service.delivery.exception.EntityAlreadyExistException;
import com.scalable.service.delivery.exception.EntityNotFoundException;
import com.scalable.service.delivery.model.Restaurant;
import com.scalable.service.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public Restaurant registerRestaurant(Restaurant restaurant) {
        restaurantRepository.findByName(restaurant.getName()).ifPresent(existingRestaurant -> {
            throw new EntityAlreadyExistException("Restaurant with name " + restaurant.getName() + " already exists");
        });

        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant updateRestaurant(String restaurantId, Restaurant updatedRestaurant) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() ->
                new EntityNotFoundException("Restaurant with ID " + restaurantId + " not found"));

        restaurant.setName(updatedRestaurant.getName());
        restaurant.setAddress(updatedRestaurant.getAddress());
        restaurant.setPhoneNumber(updatedRestaurant.getPhoneNumber());

        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> searchRestaurants(MenuItemType type, String name) {
        return restaurantRepository.findByMenuItemTypeAndName(type, name);
    }
}