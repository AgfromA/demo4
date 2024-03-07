package com.example.demo.services;
import com.example.demo.models.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface RoleService {


    @Transactional
    void save(Role role);

    List<Role> getListRoles();

    Role findByName(String name);

    Role getRoleById(Integer id);

}