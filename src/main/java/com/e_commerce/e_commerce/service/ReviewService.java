package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.dto.response.ReviewResponse;
import com.e_commerce.e_commerce.dto.request.ReviewRequest;

import java.util.List;

public interface ReviewService {
    List<ReviewResponse> getReviewsByProductId(Long productId);
    List<ReviewResponse> getReviewsByUserId(Long userId);
    ReviewResponse createReview(ReviewRequest reviewRequest);
}
