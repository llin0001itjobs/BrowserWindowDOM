package org.llin.demo.browserDOM.repository;

import org.llin.demo.browserDOM.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByName(String name);
}
