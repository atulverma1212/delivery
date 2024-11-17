//package com.scalable.service.delivery.service;
//
//import com.scalable.service.delivery.model.Order;
//import com.scalable.service.delivery.repository.OrderRepository;
//import com.scalable.service.delivery.repository.DeliveryPersonnelRepository;
//import com.scalable.service.delivery.exception.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class DeliveryService {
//
//    private final DeliveryPersonnelRepository deliveryPersonnelRepository;
//    private final OrderRepository orderRepository;
// public List<Order> getAvailableDeliveries() {
//        return orderRepository.findByStatus("Ready for Delivery");
//    }
// public void updateDeliveryStatus(String orderId, String status) {
//        Order order = orderRepository.findById(orderId).orElseThrow(() ->
//            new EntityNotFoundException("Order with ID " + orderId + " not found"));
//        order.setStatus(status);
//        orderRepository.save(order);
//    }
// public void setAvailability(Long deliveryPersonnelId, boolean availability) {
//        DeliveryPersonnel deliveryPersonnel = deliveryPersonnelRepository.findById(deliveryPersonnelId)
//                .orElseThrow(() -> new EntityNotFoundException("Delivery Personnel with ID " + deliveryPersonnelId + " not found"));
//        deliveryPersonnel.setAvailable(availability);
//        deliveryPersonnelRepository.save(deliveryPersonnel);
//    }
//}
