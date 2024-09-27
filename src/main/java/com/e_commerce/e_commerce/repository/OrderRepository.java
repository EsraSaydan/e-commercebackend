package com.e_commerce.e_commerce.repository;

import com.e_commerce.e_commerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
