package com.scalable.service.delivery.service;

import com.scalable.service.delivery.exception.EntityNotFoundException;
import com.scalable.service.delivery.model.Menu;
import com.scalable.service.delivery.model.MenuItem;
import com.scalable.service.delivery.repository.MenuItemRepository;
import com.scalable.service.delivery.repository.MenuRepository;
import com.scalable.service.delivery.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @MockBean
    private MenuRepository menuRepository;

    @MockBean
    private MenuItemRepository menuItemRepository;

    @MockBean
    private RestaurantRepository restaurantRepository;

    @Test
    public void testCreateMenuItem() {
        Menu menu = new Menu();
        MenuItem menuItem = new MenuItem();
        menu.setItems(new ArrayList<>());
        menuItem.setId("1");
        menuItem.setName("Test Item");

        Mockito.when(menuRepository.findByRestaurantId(anyString())).thenReturn(menu);
        Mockito.when(menuItemRepository.save(any(MenuItem.class))).thenReturn(menuItem);

        MenuItem createdItem = menuService.createMenuItem("restaurantId", menuItem);

        assertThat(createdItem).isNotNull();
        assertThat(createdItem.getName()).isEqualTo("Test Item");
    }

    @Test
    public void testCreateMenuItemMenuNotFound() {
        Mockito.when(menuRepository.findByRestaurantId(anyString())).thenReturn(null);

        MenuItem menuItem = new MenuItem();
        assertThrows(EntityNotFoundException.class, () -> {
            menuService.createMenuItem("restaurantId", menuItem);
        });
    }

    @Test
    public void testDeleteMenuItem() {
        Menu menu = new Menu();
        MenuItem menuItem = new MenuItem();
        menuItem.setId("1");
        menu.setItems(new ArrayList<>());

        Mockito.when(menuRepository.findByRestaurantId(anyString())).thenReturn(menu);
        Mockito.when(menuItemRepository.findById(anyString())).thenReturn(Optional.of(menuItem));

        menuService.deleteMenuItem("restaurantId", "menuItemId");

        Mockito.verify(menuItemRepository, Mockito.times(1)).delete(menuItem);
    }

    @Test
    public void testDeleteMenuItemNotFound() {
        Mockito.when(menuRepository.findByRestaurantId(anyString())).thenReturn(new Menu());
        Mockito.when(menuItemRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            menuService.deleteMenuItem("restaurantId", "menuItemId");
        });
    }

    @Test
    public void testUpdateMenuItem() {
        Menu menu = new Menu();
        MenuItem menuItem = new MenuItem();
        menuItem.setId("1");
        menuItem.setName("Updated Item");

        Mockito.when(menuRepository.findByRestaurantId(anyString())).thenReturn(menu);
        Mockito.when(menuItemRepository.findById(anyString())).thenReturn(Optional.of(menuItem));
        Mockito.when(menuItemRepository.save(any(MenuItem.class))).thenReturn(menuItem);

        MenuItem updatedItem = menuService.updateMenuItem("restaurantId", "menuItemId", menuItem);

        assertThat(updatedItem).isNotNull();
        assertThat(updatedItem.getName()).isEqualTo("Updated Item");
    }

    @Test
    public void testUpdateMenuItemNotFound() {
        Mockito.when(menuRepository.findByRestaurantId(anyString())).thenReturn(new Menu());
        Mockito.when(menuItemRepository.findById(anyString())).thenReturn(Optional.empty());

        MenuItem updatedItem = new MenuItem();
        assertThrows(EntityNotFoundException.class, () -> {
            menuService.updateMenuItem("restaurantId", "menuItemId", updatedItem);
        });
    }

    @Test
    public void testGetMenuByRestaurantId() {
        Menu menu = new Menu();
        Mockito.when(menuRepository.findByRestaurantId(anyString())).thenReturn(menu);

        Menu foundMenu = menuService.getMenuByRestaurantId("restaurantId");

        assertThat(foundMenu).isNotNull();
    }

    @Test
    public void testGetMenuByRestaurantIdNotFound() {
        Mockito.when(menuRepository.findByRestaurantId(anyString())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> {
            menuService.getMenuByRestaurantId("restaurantId");
        });
    }
}