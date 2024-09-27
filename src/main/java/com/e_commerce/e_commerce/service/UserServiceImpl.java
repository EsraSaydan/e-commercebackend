package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.converter.Converter;
import com.e_commerce.e_commerce.dto.request.UserRequest;
import com.e_commerce.e_commerce.dto.response.UserResponse;
import com.e_commerce.e_commerce.entity.User;
import com.e_commerce.e_commerce.exception.CommerceException;
import com.e_commerce.e_commerce.repository.UserRepository;
import com.e_commerce.e_commerce.validation.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void findUserByEmail(String email) {
        boolean userExist = userRepository.findUserByEmail(email).isPresent();
        if (userExist) {
            throw new CommerceException(EntityValidator.IS_EMAIL_PRESENT, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public User findUserByID(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new CommerceException(EntityValidator.IS_USER_PRESENT, HttpStatus.NOT_FOUND);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return Converter.findUsers(userRepository.findAll());
    }

    @Override
    public UserResponse saveUser(User user) {

        EntityValidator.checkEmptyOrNull(user.getName(), "name");
        EntityValidator.checkEmptyOrNull(user.getEmail(), "email");
        EntityValidator.checkEmptyOrNull(user.getPassword(), "password");

        return Converter.findUser(userRepository.save(user));
    }

    @Override
    public User deleteUser(long id) {
        User user = findUserByID(id);
        EntityValidator.IsUserPresent(user);
        userRepository.delete(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(EntityValidator.IS_USER_VALID));

        // Kullanıcı bilgileri
        System.out.println("Kullanıcı: " + user.getEmail() + ", Rol: " + user.getRole().getAuthority());

        if (user.getRole() == null) {
            throw new UsernameNotFoundException("User has no role assigned");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(),
                Collections.singletonList(new org.springframework.security.core.authority.SimpleGrantedAuthority(user.getRole().getAuthority()))
        );
    }
}
