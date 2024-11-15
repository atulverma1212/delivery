package com.scalable.service.delivery.controller;


import com.scalable.service.delivery.enums.MenuItemType;
import com.scalable.service.delivery.model.Restaurant;
import com.scalable.service.delivery.service.RestaurantService;
import jakarta.validation.Valid;
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

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable String restaurantId, @Valid @RequestBody Restaurant updatedRestaurant) {
        Restaurant updated = restaurantService.updateRestaurant(restaurantId, updatedRestaurant);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurants(
            @RequestParam MenuItemType type,
            @RequestParam String name) {
        List<Restaurant> restaurants = restaurantService.searchRestaurants(type, name);
        return ResponseEntity.ok(restaurants);
    }
}