package com.e_commerce.e_commerce.controller;

import com.e_commerce.e_commerce.dto.request.OrderRequest;
import com.e_commerce.e_commerce.dto.response.OrderResponse;
import com.e_commerce.e_commerce.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveOrder() {
        // Mock request ve response
        OrderRequest mockOrderRequest = new OrderRequest();
        mockOrderRequest.setUserName("Test User");
        mockOrderRequest.setPrice("100");

        OrderResponse mockOrderResponse = new OrderResponse();
        mockOrderResponse.setUserName("Test User");
        mockOrderResponse.setPrice("100");

        // Mock davranışı ayarla
        when(orderService.saveOrder(mockOrderRequest)).thenReturn(mockOrderResponse);

        // Testi çalıştır
        OrderResponse response = orderController.saveOrder(mockOrderRequest);

        // Beklenen sonuçları kontrol et
        assertEquals("Test User", response.getUserName());
        assertEquals("100", response.getPrice());

        // Mock nesnenin çağrıldığından emin ol
        verify(orderService, times(1)).saveOrder(mockOrderRequest);
    }
}
