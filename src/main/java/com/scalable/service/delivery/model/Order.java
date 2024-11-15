package com.scalable.service.delivery.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;

    @NotBlank(message = "Customer ID is mandatory")
    private String customerId;

    @NotBlank(message = "Restaurant ID is mandatory")
    private String restaurantId;

    @NotNull(message = "Items are mandatory")
    private List<OrderItem> items;

    @NotBlank(message = "Order status is mandatory")
    private String status;

    private Double totalPrice;

    @CreatedDate
    private LocalDateTime createdDate;
}