package com.e_commerce.e_commerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "wishlists", schema = "public")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Wishlist'in benzersiz kimliği
    private Long id;

    // Bir kullanıcıya ait istek listesi (many-to-one ilişki)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Wishlist ile ürünler arasında many-to-many ilişki
    @ManyToMany
    @JoinTable(
            name = "wishlist_product", // Ara tablo ismi
            joinColumns = @JoinColumn(name = "wishlist_id"), // Wishlist'teki foreign key
            inverseJoinColumns = @JoinColumn(name = "product_id") // Product'taki foreign key
    )
    private List<Products> products;


}
