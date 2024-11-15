package com.scalable.service.delivery.service;

import com.scalable.service.delivery.exception.EntityNotFoundException;
import com.scalable.service.delivery.model.MenuItem;
import com.scalable.service.delivery.model.Order;
import com.scalable.service.delivery.model.OrderItem;
import com.scalable.service.delivery.repository.MenuItemRepository;
import com.scalable.service.delivery.repository.OrderRepository;
import com.scalable.service.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    public Order placeOrder(Order order) {
        restaurantRepository.findById(order.getRestaurantId()).orElseThrow(() ->
                new EntityNotFoundException("Restaurant with ID " + order.getRestaurantId() + " not found"));

        double totalPrice = 0;
        for (OrderItem orderItem : order.getItems()) {
            MenuItem menuItem = menuItemRepository.findById(orderItem.getMenuItemId()).orElseThrow(() ->
                    new EntityNotFoundException("MenuItem with ID " + orderItem.getMenuItemId() + " not found"));

            if (!menuItem.getAvailability()) {
                throw new IllegalStateException("MenuItem with ID " + orderItem.getMenuItemId() + " is not available");
            }

            totalPrice += menuItem.getPrice() * orderItem.getQuantity();
        }
        order.setTotalPrice(totalPrice);
        order.setCreatedDate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public String getOrderStatus(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new EntityNotFoundException("Order with ID " + orderId + " not found"));
        return order.getStatus();
    }

    public List<Order> getOrderHistory(String customerId) {
        return orderRepository.findByCustomerIdOrderByCreatedAtDesc(customerId);
    }
}