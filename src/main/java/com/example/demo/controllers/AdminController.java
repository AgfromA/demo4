package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.RoleService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String userList(Model model) {
        model.addAttribute("usersList", userService.allUsers());
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("role", roleService.getAllRole());
        return "create";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "create";

        userService.addUser(user);

        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(value = "id", required = false) Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("role", roleService.getAllRole());
        return "edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute(value = "user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "edit";

        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("users") //удаление+
    public String delete(@RequestParam(value = "id", required = false) Long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }

}