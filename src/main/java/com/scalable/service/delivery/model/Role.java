package com.scalable.service.delivery.model;

import com.scalable.service.delivery.enums.ROLE;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.util.Set;

@Data
@Document(collection = "roles")
public class Role {
    @Id
    private String id;
    private ROLE name;
    private Set<Permission> permissions;
}
