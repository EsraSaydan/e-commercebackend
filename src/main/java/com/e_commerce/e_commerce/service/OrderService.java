package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.dto.response.OrderResponse;
import com.e_commerce.e_commerce.dto.request.OrderRequest;
import com.e_commerce.e_commerce.entity.Order;



public interface OrderService {
    OrderResponse saveOrder(OrderRequest orderRequest);

}
