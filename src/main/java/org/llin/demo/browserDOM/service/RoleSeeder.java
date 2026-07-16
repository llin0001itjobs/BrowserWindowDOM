package org.llin.demo.browserDOM.service;

import org.llin.demo.browserDOM.entity.Role;
import org.llin.demo.browserDOM.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        // Create ROLE_USER if it doesn't exist
        if (roleRepository.findRoleByType("ROLE_USER") == null) {
            Role userRole = new Role();
            userRole.setType("ROLE_USER");
            userRole.setDescription("Regular authenticated user");
            roleRepository.save(userRole);
        }
    }
}