package com.scalable.service.delivery.service;


import com.scalable.service.delivery.enums.MenuItemType;
import com.scalable.service.delivery.enums.ROLE;
import com.scalable.service.delivery.exception.EntityAlreadyExistException;
import com.scalable.service.delivery.exception.EntityNotFoundException;
import com.scalable.service.delivery.model.Restaurant;
import com.scalable.service.delivery.model.User;
import com.scalable.service.delivery.repository.RestaurantRepository;
import com.scalable.service.delivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final UserService userService;
    private final MenuService menuService;

    @Transactional
    public Restaurant registerRestaurant(Restaurant restaurant) {
        restaurantRepository.findByName(restaurant.getName()).ifPresent(existingRestaurant -> {
            throw new EntityAlreadyExistException("Restaurant with name " + restaurant.getName() + " already exists");
        });

        User restaurantUser = User.builder().firstName(restaurant.getName())
                .username(restaurant.getUsername()).password(restaurant.getPassword())
                .roles(Set.of(ROLE.RESTAURANT.getRole())).build();
        userService.saveUser(restaurantUser);

        restaurant.setUserId(restaurantUser.getId());
        restaurant.setMenu(menuService.createMenu(restaurant.getMenu()));
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