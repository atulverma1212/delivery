package com.scalable.service.delivery.controller;


import com.scalable.service.delivery.model.Customer;
import com.scalable.service.delivery.model.Order;
import com.scalable.service.delivery.service.CustomerService;
import com.scalable.service.delivery.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@Validated
public class CustomerController {
    private final CustomerService customerService;
    private final OrderService orderService;

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer) {
        Customer registeredCustomer = customerService.registerCustomer(customer);
        return ResponseEntity.ok(registeredCustomer);
    }

    @GetMapping("/{customerId}/orders")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable String customerId) {
        List<Order> orderHistory = orderService.getOrderHistory(customerId);
        return ResponseEntity.ok(orderHistory);
    }
}
