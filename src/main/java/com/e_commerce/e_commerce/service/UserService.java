package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.dto.request.UserRequest;
import com.e_commerce.e_commerce.dto.response.UserResponse;
import com.e_commerce.e_commerce.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    void findUserByEmail(String email);
    User findUserByID(long id);
    List<UserResponse> getAllUsers();
    UserResponse saveUser(User user);
    User deleteUser(long id);
}
