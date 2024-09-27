package com.e_commerce.e_commerce.repository;

import com.e_commerce.e_commerce.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindUserByEmail() {
        // Veritabanında var olan bir e-posta ile kullanıcıyı bulun
        Optional<User> userOptional = userRepository.findUserByEmail("esra@test.com");

        // Kullanıcının doğru şekilde bulunduğunu doğrulayın
        assertTrue(userOptional.isPresent(), "User should be present");

        // Kullanıcının detaylarını kontrol edin
        User user = userOptional.get();
        assertEquals("esra@test.com", user.getEmail(), "Email should match");
    }

}
