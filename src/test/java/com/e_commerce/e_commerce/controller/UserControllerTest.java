package com.e_commerce.e_commerce.controller;

import com.e_commerce.e_commerce.dto.response.UserResponse;
import com.e_commerce.e_commerce.entity.Address;
import com.e_commerce.e_commerce.entity.Role;
import com.e_commerce.e_commerce.entity.User;
import com.e_commerce.e_commerce.service.AddressService;
import com.e_commerce.e_commerce.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private AddressService addressService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test 1: getAllUsers metodu
    @Test
    void testGetAllUsers() {
        // Teste başlarken sahte bir kullanıcı listesi oluşturuyoruz.
        List<UserResponse> mockUsers = Arrays.asList(
                new UserResponse(1L, "Esra", "esra@gmail.com", "ADMIN"),
                new UserResponse(2L, "Ahmet", "ahmet@gmail.com", "USER")
        );

        // Mock davranışını ayarlıyoruz.
        when(userService.getAllUsers()).thenReturn(mockUsers);

        // Testi çalıştırıyoruz.
        ResponseEntity<List<UserResponse>> response = userController.getAllUsers();

        // Beklenen yanıtı kontrol ediyoruz.
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsers, response.getBody());

        // Mock objesinin çağrıldığından emin oluyoruz.
        verify(userService, times(1)).getAllUsers();
    }

    // Test 2: saveAddress metodu
    @Test
    void testSaveAddress() {
        // Test için sahte bir adres oluşturuyoruz.
        Address address = new Address();
        address.setName("Test Address");

        // Adresin kaydedildiğini mockluyoruz.
        when(addressService.saveAddress(address)).thenReturn("Adres kaydedildi");

        // Testi çalıştırıyoruz.
        String result = userController.saveAddress(address);

        // Beklenen sonucu kontrol ediyoruz.
        assertEquals("Adres kaydedildi", result);

        // Mock objesinin çağrıldığını doğruluyoruz.
        verify(addressService, times(1)).saveAddress(address);
    }


}
