package com.example.demo.services;


import com.example.demo.models.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);

    List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(Integer id);

    void deleteUser(Integer id);

    void updateUser(User user);

}
