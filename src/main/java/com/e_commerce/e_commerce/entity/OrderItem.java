package com.e_commerce.e_commerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cart", schema = "public")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Order ile ilişki: Bir sipariş birçok OrderItem içerebilir, her OrderItem bir siparişe aittir.
    @ManyToOne
    @JoinColumn(name = "order_id") // foreign key olarak order_id sütununu tanımlar
    private Order order;

    // Product ile ilişki: Bir OrderItem bir ürüne aittir.
    @ManyToOne
    @JoinColumn(name = "product_id") // foreign key olarak product_id sütununu tanımlar
    private Products products;

    @Column(name = "count")
    private int count;

    @Column(name = "detail")
    private String detail;
}
