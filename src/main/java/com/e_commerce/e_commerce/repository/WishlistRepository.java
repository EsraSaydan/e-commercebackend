package com.e_commerce.e_commerce.repository;

import com.e_commerce.e_commerce.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true) // Sadece okuma işlemleri için optimize edilmiş
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    // Kullanıcının favori listesine eklediği ürünleri getir
    @Query("SELECT w FROM Wishlist w WHERE w.user.id = :userId")
    List<Wishlist> findWishlistByUserId(Long userId);
}
