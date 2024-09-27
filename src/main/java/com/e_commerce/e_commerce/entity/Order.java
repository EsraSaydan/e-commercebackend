package com.e_commerce.e_commerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders", schema = "public")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "order_date")
    private String orderDate;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_month")
    private String cardMonth;

    @Column(name = "card_year")
    private String cardYear;

    @Column(name = "card_cvv")
    private String cardCvv;

    @Column(name = "price")
    private String price;

    @Column(name = "username")
    private String userName;

    // User ile ManyToOne ilişkisi
    @ManyToOne
    @JoinColumn(name = "user_id") // Sipariş ile kullanıcıyı ilişkilendiren foreign key
    private User user;

    // Address ile ManyToOne ilişkisi
    @ManyToOne
    @JoinColumn(name = "address_id") // Sipariş ile adresi ilişkilendiren foreign key
    private Address address;

    // Sipariş ile ürünler arasında many-to-many ilişki
    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinTable(name = "order_product",
            schema = "public",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Products> products;

}
