package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.dto.response.ReviewResponse;
import com.e_commerce.e_commerce.dto.request.ReviewRequest;
import com.e_commerce.e_commerce.entity.Review;
import com.e_commerce.e_commerce.entity.Products;
import com.e_commerce.e_commerce.entity.User;
import com.e_commerce.e_commerce.exception.CommerceException;
import com.e_commerce.e_commerce.repository.ReviewRepository;
import com.e_commerce.e_commerce.repository.ProductRepository;
import com.e_commerce.e_commerce.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ReviewResponse> getReviewsByProductId(Long productId) {
        return reviewRepository.findReviewsByProductId(productId).stream()
                .map(review -> new ReviewResponse(review.getId(), review.getComment(), review.getRating(), review.getProduct().getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponse> getReviewsByUserId(Long userId) {
        return reviewRepository.findReviewsByUserId(userId).stream()
                .map(review -> new ReviewResponse(review.getId(), review.getComment(), review.getRating(), review.getProduct().getName()))
                .collect(Collectors.toList());
    }

    @Override
    public ReviewResponse createReview(ReviewRequest reviewRequest) {
        User user = userRepository.findById(reviewRequest.getUserId())
                .orElseThrow(() -> new CommerceException("User not found", HttpStatus.NOT_FOUND));

        Products product = productRepository.findById(reviewRequest.getProductId())
                .orElseThrow(() -> new CommerceException("Product not found", HttpStatus.NOT_FOUND));

        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setComment(reviewRequest.getComment());
        review.setRating(reviewRequest.getRating());
        reviewRepository.save(review);

        return new ReviewResponse(review.getId(), review.getComment(), review.getRating(), review.getProduct().getName());
    }
}
