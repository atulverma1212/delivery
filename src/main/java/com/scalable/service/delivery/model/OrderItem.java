package com.scalable.service.delivery.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @NotBlank(message = "Menu Item ID is mandatory")
    private String menuItemId;

    @NotNull(message = "Quantity is mandatory")
    private Integer quantity;
}