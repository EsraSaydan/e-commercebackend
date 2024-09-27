package com.e_commerce.e_commerce.controller;

import com.e_commerce.e_commerce.converter.Converter;
import com.e_commerce.e_commerce.dto.request.UserRequest;
import com.e_commerce.e_commerce.dto.response.UserResponse;
import com.e_commerce.e_commerce.entity.Address;
import com.e_commerce.e_commerce.entity.Role;
import com.e_commerce.e_commerce.entity.User;
import com.e_commerce.e_commerce.repository.RoleRepository;
import com.e_commerce.e_commerce.service.AddressService;
import com.e_commerce.e_commerce.service.UserService;
import com.e_commerce.e_commerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AddressService addressService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    public UserController(UserService userService, AddressService addressService, UserRepository userRepository, RoleRepository roleRepository) {
        this.userService = userService;
        this.addressService = addressService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/address")
    public String saveAddress(@RequestBody Address address) {
        return addressService.saveAddress(address);
    }

    @GetMapping("/address")
    public List<Address> getAllAddress() {
        return addressService.getAllAddress();
    }

    // ADMIN rolündeki kullanıcıları listeleyen method
    @GetMapping("/admin/all")
    @ResponseBody
    public ResponseEntity<List<UserResponse>> getAllAdmins() {
        logger.info("Admin kullanıcıları getirme işlemi başladı.");

        // Birden fazla ADMIN rolü için repository'den liste çekiyoruz
        List<Role> adminRoles = roleRepository.findByAuthority("ADMIN");

        if (adminRoles.isEmpty()) {
            throw new RuntimeException("Admin rolü bulunamadı");
        }

        logger.info("Admin rolleri bulundu: {}", adminRoles);

        // ADMIN rolüne sahip tüm kullanıcıları buluyoruz
        List<User> admins = userRepository.findAll().stream()
                .filter(user -> adminRoles.contains(user.getRole()))
                .toList();

        logger.info("Admin kullanıcılar: {}", admins);

        // Kullanıcıları response'a dönüştürüyoruz
        List<UserResponse> adminResponses = Converter.findUsers(admins);

        return new ResponseEntity<>(adminResponses, HttpStatus.OK);
    }


}