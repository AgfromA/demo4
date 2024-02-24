package com.example.demo.services;

import com.example.demo.models.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService
{ List<Role> getAllRole();
    Role findByName(String name);
    void save(Role role);
    void add(Role role);

}
