package com.scalable.service.delivery.service;

import com.scalable.service.delivery.dto.AuthResponse;
import com.scalable.service.delivery.dto.SignUpRequest;
import com.scalable.service.delivery.dto.SigninRequest;
import com.scalable.service.delivery.model.Role;
import com.scalable.service.delivery.model.User;
import com.scalable.service.delivery.repository.RoleRepository;
import com.scalable.service.delivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthResponse signup(SignUpRequest request) {

        Role role = roleRepository.findByName("USER");
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .username(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(role))
                .build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user.toUserDetails());
        return AuthResponse.builder().token(jwt).build();
    }

    public AuthResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByUsername(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user.toUserDetails());
        return AuthResponse.builder().token(jwt).build();
    }
}