package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AuthController {
    @GetMapping(value = "/")
    public String printWelcome() {
        return "redirect:/login";
    }
}


