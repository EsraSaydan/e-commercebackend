package com.e_commerce.e_commerce.repository;

import com.e_commerce.e_commerce.entity.Role;
import com.e_commerce.e_commerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

    // Kullanıcının email adresine göre User nesnesini bulur ve role ile birlikte fetch eder
    @Query("SELECT u FROM User u JOIN FETCH u.role WHERE u.email = :email")
    Optional<User> findUserByEmail(String email);

    List<User> findByRole(Role role);
}
