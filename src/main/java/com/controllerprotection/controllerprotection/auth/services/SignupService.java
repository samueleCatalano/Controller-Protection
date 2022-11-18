package com.controllerprotection.controllerprotection.auth.services;

import com.controllerprotection.controllerprotection.auth.entities.SignupActivationDTO;
import com.controllerprotection.controllerprotection.auth.entities.SignupDTO;
import com.controllerprotection.controllerprotection.entities.Role;
import com.controllerprotection.controllerprotection.entities.Roles;
import com.controllerprotection.controllerprotection.entities.User;
import com.controllerprotection.controllerprotection.repositories.RoleRepository;
import com.controllerprotection.controllerprotection.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class SignupService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailNotificationService mailNotificationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

   public User signup(SignupDTO signupDTO) throws Exception{
       User userInDB = userRepository.findByEmail(signupDTO.getEmail());
       if(userInDB != null) throw new Exception("The user already exists");
       User user = new User();
       user.setName(signupDTO.getName());
       user.setSurname(signupDTO.getSurname());
       user.setEmail(signupDTO.getEmail());
       user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
       user.setActivationCode(UUID.randomUUID().toString());
       Set<Role> roles = new HashSet<>();
       Optional<Role> userRole = roleRepository.findByName(Roles.REGISTERED);
       if(!userRole.isPresent()) throw  new Exception("Cannot set role");
       roles.add(userRole.get());
       user.setRoles(roles);
       mailNotificationService.sendActivationEmail(user);
       return userRepository.save(user);
   }

   public User activate(SignupActivationDTO signupActivationDTO) throws Exception{
       User user = userRepository.findByActivationCode(signupActivationDTO.getActivationCode());
       if(user == null) throw new Exception("Cannot find the user");
       user.setActive(true);
       user.setActivationCode(signupActivationDTO.getActivationCode());
       return userRepository.save(user);
   }
}
