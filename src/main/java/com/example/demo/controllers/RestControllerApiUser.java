package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class RestControllerApiUser {
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public RestControllerApiUser(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    @GetMapping()
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        User user = userServiceImpl.findByUsername(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
