package com.e_commerce.e_commerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "reviews", schema = "public")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Değerlendirmenin benzersiz kimliği

    @Column(nullable = false)
    private String comment; // Ürüne yapılan yorum

    @Column(nullable = false)
    private Integer rating; // Ürün için verilen puan

    // Değerlendirmeyi yapan kullanıcıyla ilişki
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Değerlendirilen ürünle ilişki
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;

    // Yorumun yapıldığı tarih
    @Column(nullable = false)
    private LocalDateTime reviewDate;
}
