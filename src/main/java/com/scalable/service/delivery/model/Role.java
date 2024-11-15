package com.scalable.service.delivery.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.util.Set;

@Data
@Document(collection = "roles")
public class Role {
    @Id
    private String id;
    private String name;
    private Set<Permission> permissions;
}
