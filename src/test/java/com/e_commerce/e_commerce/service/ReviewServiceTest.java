package com.e_commerce.e_commerce.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.e_commerce.e_commerce.dto.request.ReviewRequest;
import com.e_commerce.e_commerce.dto.response.ReviewResponse;
import com.e_commerce.e_commerce.entity.Review;
import com.e_commerce.e_commerce.entity.Products;
import com.e_commerce.e_commerce.entity.User;
import com.e_commerce.e_commerce.exception.CommerceException;
import com.e_commerce.e_commerce.repository.ReviewRepository;
import com.e_commerce.e_commerce.repository.ProductRepository;
import com.e_commerce.e_commerce.repository.UserRepository;
import com.e_commerce.e_commerce.service.ReviewServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetReviewsByProductId() {
        // Arrange
        Long productId = 1L;
        Products product = new Products();
        product.setId(productId);
        Review review = new Review();
        review.setId(1L);
        review.setProduct(product);
        review.setComment("Great product");
        review.setRating(5);

        when(reviewRepository.findReviewsByProductId(productId)).thenReturn(Stream.of(review).collect(Collectors.toList()));

        // Act
        List<ReviewResponse> responses = reviewService.getReviewsByProductId(productId);

        // Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("Great product", responses.get(0).getComment());
        assertEquals(5, responses.get(0).getRating());
    }
    @Test
    void testGetReviewsByUserId() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        Products product = new Products();
        product.setId(1L);
        product.setName("Test Product");

        Review review = new Review();
        review.setId(1L);
        review.setUser(user);
        review.setProduct(product);
        review.setComment("Great product");
        review.setRating(5);

        when(reviewRepository.findReviewsByUserId(userId)).thenReturn(Stream.of(review).collect(Collectors.toList()));
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        // Act
        List<ReviewResponse> responses = reviewService.getReviewsByUserId(userId);

        // Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("Great product", responses.get(0).getComment());
        assertEquals(5, responses.get(0).getRating());
        assertEquals("Test Product", responses.get(0).getProductName());
    }



    @Test
    void testCreateReview() {
        // Arrange
        Long userId = 1L;
        Long productId = 1L;
        ReviewRequest reviewRequest = new ReviewRequest(productId, userId, "Awesome product", 5);

        User user = new User();
        user.setId(userId);
        Products product = new Products();
        product.setId(productId);

        Review review = new Review();
        review.setId(1L);
        review.setUser(user);
        review.setProduct(product);
        review.setComment("Awesome product");
        review.setRating(5);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        // Act
        ReviewResponse response = reviewService.createReview(reviewRequest);

        // Assert
        assertNotNull(response);
        assertEquals("Awesome product", response.getComment());
        assertEquals(5, response.getRating());
    }

    @Test
    void testCreateReview_UserNotFound() {
        // Arrange
        Long userId = 1L;
        Long productId = 1L;
        ReviewRequest reviewRequest = new ReviewRequest(productId, userId, "Nice product", 4);

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        CommerceException exception = assertThrows(CommerceException.class, () -> {
            reviewService.createReview(reviewRequest);
        });

        assertEquals("User not found", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void testCreateReview_ProductNotFound() {
        // Arrange
        Long userId = 1L;
        Long productId = 1L;
        ReviewRequest reviewRequest = new ReviewRequest(productId, userId, "Nice product", 4);

        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act & Assert
        CommerceException exception = assertThrows(CommerceException.class, () -> {
            reviewService.createReview(reviewRequest);
        });

        assertEquals("Product not found", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }
}

