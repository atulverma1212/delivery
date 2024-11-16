package com.scalable.service.delivery.controller;

import com.scalable.service.delivery.dto.UpdateOrderStatusRequest;
import com.scalable.service.delivery.enums.OrderStatus;
import com.scalable.service.delivery.model.Order;
import com.scalable.service.delivery.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Validated
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Order> placeOrder(@Valid @RequestBody Order order) {
        Order placedOrder = orderService.placeOrder(order);
        return ResponseEntity.ok(placedOrder);
    }

    @GetMapping("/{orderId}/status")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'RESTAURANT', 'DELIVERY_EXPERT')")
    public ResponseEntity<Order> getOrderStatus(@PathVariable String orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @PutMapping("/{orderId}/status")
    @PreAuthorize("hasRole('RESTAURANT')")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable String orderId, @RequestBody UpdateOrderStatusRequest request) {
        Order updatedOrder = orderService.updateOrderStatus(orderId, request.status());
        return ResponseEntity.ok(updatedOrder);
    }
}