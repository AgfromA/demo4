package com.example.demo.dbinit;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {

    private RoleServiceImpl roleService;
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public Init(RoleServiceImpl roleService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        String adminRoleName = "ROLE_ADMIN";
        String userRoleName = "ROLE_USER";

        Role userRole = roleService.findByName(userRoleName);
        Role adminRole = roleService.findByName(adminRoleName);


        // Если обе роли уже существуют, не создавать их снова
        if (userRole == null && adminRole == null) {

            userRole = new Role();
            userRole.setName(userRoleName);
            roleService.save(userRole);


            adminRole = new Role();
            adminRole.setName(adminRoleName);
            roleService.save(adminRole);


            Set<Role> userRoles = new HashSet<>(Arrays.asList(userRole));
            Set<Role> adminRoles = new HashSet<>(Arrays.asList(adminRole));


            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setAge(18);
            admin.setEmail("admin@mail.ru");
            admin.setRoles(adminRoles);
            admin.setLastname("Molotov");

            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setAge(25);
            user.setEmail("user@mail.ru");
            user.setRoles(userRoles);
            user.setLastname("Molotov");

            userRepository.save(admin);
            userRepository.save(user);
        }
    }
}