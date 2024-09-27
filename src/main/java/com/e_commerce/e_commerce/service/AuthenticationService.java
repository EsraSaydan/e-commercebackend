package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.converter.Converter;
import com.e_commerce.e_commerce.dto.request.LoginRequest;
import com.e_commerce.e_commerce.dto.response.UserResponse;
import com.e_commerce.e_commerce.entity.Role;
import com.e_commerce.e_commerce.entity.User;
import com.e_commerce.e_commerce.exception.CommerceException;
import com.e_commerce.e_commerce.repository.RoleRepository;
import com.e_commerce.e_commerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse signup(String name, String email, String password, String role) {
        // Role'ü roleRepository'den buluyoruz
        List<Role> roles = roleRepository.findByAuthority(role);

        if (roles.isEmpty()) {
            throw new CommerceException("Role mustn't be empty", HttpStatus.BAD_REQUEST);
        }
        Role userRole = roles.get(0);
        String encodedPassword = passwordEncoder.encode(password);

        // Yeni bir kullanıcı oluşturup kaydediyoruz
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setRole(userRole);
        userRepository.save(user);

        // Kullanıcıyı response'a dönüştürüyoruz
        return Converter.findUser(user);
    }

    public UserResponse login(LoginRequest loginRequest) {
        // Kullanıcıyı e-posta ile buluyoruz
        User user = userRepository.findUserByEmail(loginRequest.email())
                .orElseThrow(() -> new CommerceException("Invalid email or password", HttpStatus.UNAUTHORIZED));

        // Şifreyi kontrol ediyoruz
        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new CommerceException("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }

        // Giriş başarılı, kullanıcıyı response'a dönüştürüyoruz
        return Converter.findUser(user);
    }
}
