package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "")
    public String userInfo(Model model, Principal principal) {
        Optional<User> userOptional = userRepository.findUserByUsername(principal.getName());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);
        } else {
            return "redirect:/login";
        }
        return "user";
    }

}
