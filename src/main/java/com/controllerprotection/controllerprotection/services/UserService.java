package com.controllerprotection.controllerprotection.services;

import com.controllerprotection.controllerprotection.repositories.UserRepository;
import com.controllerprotection.controllerprotection.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        userRepository.findAll();
        return null;
    }

    public void findById(Long id) {
        userRepository.findById(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

}
