package com.e_commerce.e_commerce.controller;

import com.e_commerce.e_commerce.dto.request.OrderRequest;
import com.e_commerce.e_commerce.dto.response.OrderResponse;
import com.e_commerce.e_commerce.service.OrderService;
import com.e_commerce.e_commerce.service.OrderItemService;
import com.e_commerce.e_commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, OrderItemService orderItemService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
        this.orderItemService = orderItemService;
    }

    @PostMapping("/")
    public OrderResponse saveOrder(@RequestBody OrderRequest order) {

        return orderService.saveOrder(order);
    }
}