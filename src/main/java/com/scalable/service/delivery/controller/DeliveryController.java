//package com.scalable.service.delivery.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.scalable.service.delivery.service.DeliveryPersonnelService;
//import com.scalable.service.delivery.service.JwtService;
//
//@RestController
//@RequestMapping("/delivery")
//public class DeliveryController {
//
//    @Autowired
//    private DeliveryPersonnelService deliveryPersonnelService;
//
//    @Autowired
//    private JwtService jwtService;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
////    @PostMapping("/register")
////    public String registerDeliveryPersonnel(@RequestBody DeliveryPersonnel deliveryPersonnel) {
////        deliveryPersonnelService.registerDeliveryPersonnel(deliveryPersonnel);
////        return "Delivery Personnel registered successfully";
////    }
//
////    @PostMapping("/login")
////    public String loginDeliveryPersonnel(@RequestBody DeliveryPersonnel deliveryPersonnel) {
////        Authentication authentication = authenticationManager.authenticate(
////                new UsernamePasswordAuthenticationToken(deliveryPersonnel.getEmail(), deliveryPersonnel.getPassword())
////        );
////        String jwt = jwtService.generateToken(authentication.getName());
////        return jwt;
////    }
//}
