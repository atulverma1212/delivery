package com.scalable.service.delivery.enums;

import com.scalable.service.delivery.model.Permission;
import com.scalable.service.delivery.model.Role;

import java.util.Set;

public enum ROLE {
    USER,
    CUSTOMER,
    RESTAURANT,
    DELIVERY_EXPERT,
    ADMIN;

    public Role getRole() {
        return Role.builder().name(this).build();
    }
}
