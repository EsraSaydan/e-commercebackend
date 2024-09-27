package com.e_commerce.e_commerce.repository;

import com.e_commerce.e_commerce.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.authority = :authority")
    List<Role> findByAuthority(String authority); // Bu method role authority'sine göre doğru veriyi döndürür.
}
