package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.dto.request.WishlistRequest;
import com.e_commerce.e_commerce.dto.response.WishlistResponse;
import com.e_commerce.e_commerce.entity.Products;
import com.e_commerce.e_commerce.entity.User;
import com.e_commerce.e_commerce.entity.Wishlist;
import com.e_commerce.e_commerce.exception.CommerceException;
import com.e_commerce.e_commerce.repository.WishlistRepository;
import com.e_commerce.e_commerce.repository.ProductRepository;
import com.e_commerce.e_commerce.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class WishlistServiceTest {

    @Mock
    private WishlistRepository wishlistRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private WishlistServiceImpl wishlistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWishlistByUserId() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setName("Test User");

        Products product = new Products();
        product.setId(1L);
        product.setName("Test Product");

        Wishlist wishlist = new Wishlist();
        wishlist.setId(1L);
        wishlist.setUser(user);
        wishlist.setProducts(List.of(product));

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(wishlistRepository.findWishlistByUserId(1L)).thenReturn(List.of(wishlist));

        // Act
        List<WishlistResponse> wishlistResponses = wishlistService.getWishlistByUserId(1L);

        // Assert
        assertNotNull(wishlistResponses);
        assertEquals(1, wishlistResponses.size());
        assertEquals("Test User", wishlistResponses.get(0).getName());
        verify(wishlistRepository, times(1)).findWishlistByUserId(1L);
    }

    @Test
    void testAddProductToWishlist() {
        // Arrange
        WishlistRequest wishlistRequest = new WishlistRequest(1L, 1L);

        User user = new User();
        user.setId(1L);
        user.setName("Test User");

        Products product = new Products();
        product.setId(1L);
        product.setName("Test Product");

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setProducts(new ArrayList<>());

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(wishlistRepository.findWishlistByUserId(1L)).thenReturn(List.of(wishlist));

        // Act
        WishlistResponse wishlistResponse = wishlistService.addProductToWishlist(wishlistRequest);

        // Assert
        assertNotNull(wishlistResponse);
        assertEquals(1, wishlistResponse.getProductCount());
        verify(wishlistRepository, times(1)).save(any(Wishlist.class));
    }

    @Test
    void testRemoveProductFromWishlist() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setName("Test User");

        Products product = new Products();
        product.setId(1L);
        product.setName("Test Product");

        Wishlist wishlist = new Wishlist();
        wishlist.setId(1L);
        wishlist.setUser(user);
        wishlist.setProducts(List.of(product));

        when(wishlistRepository.findById(1L)).thenReturn(Optional.of(wishlist));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Act
        WishlistResponse wishlistResponse = wishlistService.removeProductFromWishlist(1L, 1L);

        // Assert
        assertNotNull(wishlistResponse);
        assertEquals(0, wishlistResponse.getProductCount());
        verify(wishlistRepository, times(1)).save(any(Wishlist.class));
    }

    @Test
    void testDeleteWishlist() {
        // Arrange
        when(wishlistRepository.existsById(1L)).thenReturn(true);

        // Act
        wishlistService.deleteWishlist(1L);

        // Assert
        verify(wishlistRepository, times(1)).deleteById(1L);
    }
}
