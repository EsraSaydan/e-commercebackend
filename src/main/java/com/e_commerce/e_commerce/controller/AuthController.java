package com.e_commerce.e_commerce.controller;

import com.e_commerce.e_commerce.dto.request.LoginRequest;
import com.e_commerce.e_commerce.dto.request.UserRequest;
import com.e_commerce.e_commerce.dto.response.LoginResponse;
import com.e_commerce.e_commerce.dto.response.UserResponse;
import com.e_commerce.e_commerce.service.AuthenticationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = authenticationService.signup(
                userRequest.name(), userRequest.email(), userRequest.password(), userRequest.role());
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        try {
            if (loginRequest.email() == null || loginRequest.email().isEmpty()) {
                return new ResponseEntity<>(new LoginResponse("Email alanı boş olamaz.", null), HttpStatus.BAD_REQUEST);
            }

            // Kullanıcıyı authenticationService ile doğrula
            UserResponse userResponse = authenticationService.login(loginRequest);

            // Giriş başarılıysa session'da user bilgilerini sakla
            session.setAttribute("user", userResponse);

            // Kullanıcının rolüne göre yanıt döndür
            switch (userResponse.role()) {
                case "ADMIN":
                    return ResponseEntity.ok(new LoginResponse("Login successful", "ADMIN"));
                case "USER":
                    return ResponseEntity.ok(new LoginResponse("Login successful", "USER"));
                default:
                    return new ResponseEntity<>(new LoginResponse("Login failed: Unknown role", null), HttpStatus.FORBIDDEN);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new LoginResponse("Login failed: " + e.getMessage(), null), HttpStatus.UNAUTHORIZED);
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully.");
    }
}