package com.scalable.service.delivery.controller;


import com.scalable.service.delivery.model.Restaurant;
import com.scalable.service.delivery.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("/register")
    public ResponseEntity<Restaurant> registerRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant registeredRestaurant = restaurantService.registerRestaurant(restaurant);
        return ResponseEntity.ok(registeredRestaurant);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }
}