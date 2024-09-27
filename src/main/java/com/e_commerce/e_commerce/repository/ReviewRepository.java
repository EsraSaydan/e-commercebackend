package com.e_commerce.e_commerce.repository;

import com.e_commerce.e_commerce.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Ürün ID'sine göre ürün değerlendirmelerini getir
    @Query("SELECT r FROM Review r WHERE r.product.id = :productId")
    List<Review> findReviewsByProductId(Long productId);

    // Kullanıcı ID'sine göre kullanıcı değerlendirmelerini getir
    @Query("SELECT r FROM Review r WHERE r.user.id = :userId")
    List<Review> findReviewsByUserId(Long userId);
}
