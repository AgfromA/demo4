package com.example.demo.services;

import com.example.demo.models.Role;
import com.example.demo.repositories.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    @Transactional
    public void save(Role role) {
        roleRepository.save(role);

    }
    @Override
    @Transactional(readOnly = true)
    public List<Role> getListRoles() {
        return roleRepository.findAll();
    }
    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
    @Override
    @Transactional(readOnly = true)
    public Role getRoleById(Integer id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        return roleOptional.orElse(null);
    }

}
