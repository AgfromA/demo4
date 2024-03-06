package com.example.demo.services;

import com.example.demo.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
     List<User> allUsers();

   User getUserById(Long id);

     User findUserByUsername(String username);

    void  addUser(User user);

     void updateUser(User user, String newPassword);

   void removeUser(Long id);


}
