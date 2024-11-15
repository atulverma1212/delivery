package com.scalable.service.delivery.service;

import com.scalable.service.delivery.exception.EntityNotFoundException;
import com.scalable.service.delivery.model.Menu;
import com.scalable.service.delivery.model.MenuItem;
import com.scalable.service.delivery.repository.MenuItemRepository;
import com.scalable.service.delivery.repository.MenuRepository;
import com.scalable.service.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuItem createMenuItem(String restaurantId, MenuItem menuItem) {
        Menu menu = menuRepository.findByRestaurantId(restaurantId);
        if (menu == null) {
            throw new EntityNotFoundException("Menu for Restaurant with ID " + restaurantId + " not found");
        }
        menuItemRepository.save(menuItem);
        menu.getItems().add(menuItem);
        menuRepository.save(menu);
        return menuItem;
    }

    public void deleteMenuItem(String restaurantId, String menuItemId) {
        Menu menu = menuRepository.findByRestaurantId(restaurantId);
        if (menu == null) {
            throw new EntityNotFoundException("Menu for Restaurant with ID " + restaurantId + " not found");
        }
        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElseThrow(() ->
                new EntityNotFoundException("MenuItem with ID " + menuItemId + " not found"));
        menu.getItems().remove(menuItem);
        menuItemRepository.delete(menuItem);
        menuRepository.save(menu);
    }

    public MenuItem updateMenuItem(String restaurantId, String menuItemId, MenuItem updatedItem) {
        Menu menu = menuRepository.findByRestaurantId(restaurantId);
        if (menu == null) {
            throw new EntityNotFoundException("Menu for Restaurant with ID " + restaurantId + " not found");
        }
        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElseThrow(() ->
                new EntityNotFoundException("MenuItem with ID " + menuItemId + " not found"));

        menuItem.setName(updatedItem.getName());
        menuItem.setDescription(updatedItem.getDescription());
        menuItem.setPrice(updatedItem.getPrice());
        menuItem.setAvailability(updatedItem.getAvailability());

        return menuItemRepository.save(menuItem);
    }

    public Menu getMenuByRestaurantId(String restaurantId) {
        Menu menu = menuRepository.findByRestaurantId(restaurantId);
        if (menu == null) {
            throw new EntityNotFoundException("Menu for Restaurant with ID " + restaurantId + " not found");
        }
        return menu;
    }
}