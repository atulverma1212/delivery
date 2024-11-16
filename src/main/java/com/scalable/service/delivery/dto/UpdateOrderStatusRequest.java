package com.scalable.service.delivery.dto;

import com.scalable.service.delivery.enums.OrderStatus;


public record UpdateOrderStatusRequest(OrderStatus status) {
}
