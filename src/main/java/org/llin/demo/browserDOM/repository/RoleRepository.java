package org.llin.demo.browserDOM.repository;

import java.util.Optional;

import org.llin.demo.browserDOM.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findRoleByType(String type);
}
