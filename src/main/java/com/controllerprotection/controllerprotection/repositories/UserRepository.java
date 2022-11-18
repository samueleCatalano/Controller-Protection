package com.controllerprotection.controllerprotection.repositories;


import com.controllerprotection.controllerprotection.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByActivationCode(String activationCode);

    User findByPasswordResetCode(String passwordResetCode);

}
