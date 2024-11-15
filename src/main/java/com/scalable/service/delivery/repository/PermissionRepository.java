package com.scalable.service.delivery.repository;

import com.scalable.service.delivery.model.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PermissionRepository extends MongoRepository<Permission, String> {
    Permission findByName(String name);
}
