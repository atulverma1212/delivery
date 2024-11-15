package com.scalable.service.delivery.controller;

import com.scalable.service.delivery.model.Menu;
import com.scalable.service.delivery.model.MenuItem;
import com.scalable.service.delivery.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
@Validated
public class MenuItemController {
    private final MenuService menuService;

    @PostMapping("/{restaurantId}/menu/item")
    public ResponseEntity<MenuItem> createMenuItem(@PathVariable String restaurantId, @Valid @RequestBody MenuItem menuItem) {
        MenuItem createdMenuItem = menuService.createMenuItem(restaurantId, menuItem);
        return ResponseEntity.ok(createdMenuItem);
    }

    @DeleteMapping("/{restaurantId}/menu/item/{menuItemId}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable String restaurantId, @PathVariable String menuItemId) {
        menuService.deleteMenuItem(restaurantId, menuItemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{restaurantId}/menu")
    public ResponseEntity<Menu> getMenuByRestaurantId(@PathVariable String restaurantId) {
        Menu menu = menuService.getMenuByRestaurantId(restaurantId);
        return ResponseEntity.ok(menu);
    }

    @PutMapping("/{restaurantId}/menu/item/{menuItemId}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable String restaurantId, @PathVariable String menuItemId,
                                                   @Valid @RequestBody MenuItem updatedItem) {
        MenuItem updatedMenuItem = menuService.updateMenuItem(restaurantId, menuItemId, updatedItem);
        return ResponseEntity.ok(updatedMenuItem);
    }
}