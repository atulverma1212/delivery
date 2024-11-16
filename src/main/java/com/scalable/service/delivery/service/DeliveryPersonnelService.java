//package com.scalable.service.delivery.service;
//
//import com.scalable.service.delivery.model.DeliveryPersonnel;
//import com.scalable.service.delivery.repository.DeliveryPersonnelRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class DeliveryPersonnelService {
//    @Autowired
//    private DeliveryPersonnelRepository deliveryPersonnelRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public DeliveryPersonnel registerDeliveryPersonnel(DeliveryPersonnel deliveryPersonnel) {
//        if (deliveryPersonnelRepository.findByEmail(deliveryPersonnel.getEmail()).isPresent()) {
//            throw new IllegalArgumentException("Delivery Personnel already exists with this email");
//        }
//        deliveryPersonnel.setPassword(passwordEncoder.encode(deliveryPersonnel.getPassword()));
////        return deliveryPersonnelRepository.save(deliveryPersonnel);
//        return null;
//    }
//
//    public Optional<DeliveryPersonnel> findByEmail(String email) {
//        return deliveryPersonnelRepository.findByEmail(email);
//    }
//}
