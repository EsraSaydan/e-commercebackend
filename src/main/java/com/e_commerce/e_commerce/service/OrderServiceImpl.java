package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.dto.response.OrderResponse;
import com.e_commerce.e_commerce.dto.request.OrderRequest;
import com.e_commerce.e_commerce.entity.Order;
import com.e_commerce.e_commerce.entity.Products;
import com.e_commerce.e_commerce.repository.OrderRepository;
import com.e_commerce.e_commerce.repository.ProductRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRespository;
    private ModelMapperService modelMapperService;
    private ProductRepository productRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRespository,ModelMapperService modelMapperService,ProductRepository productRepository) {
        this.orderRespository = orderRespository;
        this.modelMapperService=modelMapperService;
        this.productRepository=productRepository;
    }

    @Override
    public OrderResponse saveOrder(OrderRequest orderRequest) {
        Order order= modelMapperService.forRequest().map(orderRequest, Order.class);
        List<Products> productsList=order.getProducts();
        order.setProducts(null);
        Order savedOrder=orderRespository.save(order);
        savedOrder.setProducts(productsList);
        orderRespository.save(savedOrder);
        return modelMapperService.forResponse().map(savedOrder, OrderResponse.class);
    }


}