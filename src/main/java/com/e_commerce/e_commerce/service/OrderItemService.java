package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItem saveOrderItem(OrderItem orderItem);
    List<OrderItem> getAllProducts();
}
