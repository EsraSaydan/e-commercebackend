package com.e_commerce.e_commerce.service;


import com.e_commerce.e_commerce.entity.Role;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    List<Role> getRoles();
}
