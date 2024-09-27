package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.dto.response.WishlistResponse;
import com.e_commerce.e_commerce.dto.request.WishlistRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;



public interface WishlistService {
    List<WishlistResponse> getWishlistByUserId(Long userId);
    WishlistResponse addProductToWishlist(WishlistRequest wishlistRequest);
    WishlistResponse removeProductFromWishlist(Long wishlistId, Long productId);
    void deleteWishlist(Long id);
}
