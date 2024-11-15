package com.scalable.service.delivery.repository;

import com.scalable.service.delivery.model.MenuItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MenuItemRepository extends MongoRepository<MenuItem, String> {
}