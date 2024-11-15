package com.scalable.service.delivery.repository;

import com.scalable.service.delivery.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
