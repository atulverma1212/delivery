package com.scalable.service.delivery.repository;

import com.scalable.service.delivery.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByName(String name);
}