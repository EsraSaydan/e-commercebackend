package com.e_commerce.e_commerce.controller;

import com.e_commerce.e_commerce.converter.Converter;
import com.e_commerce.e_commerce.dto.response.RoleResponse;
import com.e_commerce.e_commerce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleResponse>> findAll() {
        List<RoleResponse> roles = Converter.findRoles(roleService.getRoles());
        return ResponseEntity.ok(roles);
    }
}