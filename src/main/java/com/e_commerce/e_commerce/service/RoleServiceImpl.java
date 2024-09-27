package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.entity.Role;
import com.e_commerce.e_commerce.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public List<Role> getRoles() {
        List<Role> roleAdmin = roleRepository.findByAuthority("ADMIN");
        List<Role> roleUser = roleRepository.findByAuthority("USER");

        List<Role> roles = new ArrayList<>();

        if (!roleAdmin.isEmpty()) {
            roles.addAll(roleAdmin);
        }
        if (!roleUser.isEmpty()) {
            roles.addAll(roleUser);
        }

        return roles;
    }
}
