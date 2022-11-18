package com.controllerprotection.controllerprotection.repositories;

import com.controllerprotection.controllerprotection.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String registered);
}
