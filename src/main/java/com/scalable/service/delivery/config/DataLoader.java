package com.scalable.service.delivery.config;
import com.scalable.service.delivery.enums.ROLE;
import com.scalable.service.delivery.model.Permission;
import com.scalable.service.delivery.model.Role;
import com.scalable.service.delivery.model.User;
import com.scalable.service.delivery.repository.PermissionRepository;
import com.scalable.service.delivery.repository.RoleRepository;
import com.scalable.service.delivery.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;
@Component
public class DataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final UserService userService;

    public DataLoader(RoleRepository roleRepository, PermissionRepository permissionRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        permissionRepository.deleteAll();
        roleRepository.deleteAll();
        userService.deleteUserByUserName("admin");
        // Create Permissions
        Permission readPermission = new Permission();
        readPermission.setName("READ_PRIVILEGE");
        permissionRepository.save(readPermission);
        Permission writePermission = new Permission();
        writePermission.setName("WRITE_PRIVILEGE");
        permissionRepository.save(writePermission);
        Set<Permission> readWritePermissions = Set.of(readPermission, writePermission);
        Role adminRole = roleRepository.save(Role.builder().name(ROLE.ADMIN).permissions(readWritePermissions).build());
        roleRepository.save(Role.builder().name(ROLE.CUSTOMER).permissions(readWritePermissions).build());
        roleRepository.save(Role.builder().name(ROLE.RESTAURANT).permissions(readWritePermissions).build());
        roleRepository.save(Role.builder().name(ROLE.DELIVERY_EXPERT).permissions(readWritePermissions).build());
        roleRepository.save(Role.builder().name(ROLE.USER).permissions(Set.of(readPermission)).build());

        // Create Users
        User adminUser = User.builder()
                .username("admin").password("admin123")
                .build();
        adminUser.setUsername("admin");
        adminUser.setPassword("admin123");
        adminUser.setRoles(Set.of(adminRole));
        userService.saveUser(adminUser);

    }
}