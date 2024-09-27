package com.e_commerce.e_commerce.controller;

import com.e_commerce.e_commerce.dto.request.WishlistRequest;
import com.e_commerce.e_commerce.dto.response.WishlistResponse;
import com.e_commerce.e_commerce.service.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping
    public ResponseEntity<WishlistResponse> addToWishlist(@RequestBody WishlistRequest wishlistRequest) {
        WishlistResponse wishlistResponse = wishlistService.addProductToWishlist(wishlistRequest);
        return new ResponseEntity<>(wishlistResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<WishlistResponse>> getWishlistByUserId(@PathVariable Long userId) {
        List<WishlistResponse> wishlist = wishlistService.getWishlistByUserId(userId);
        return ResponseEntity.ok(wishlist);
    }

    @DeleteMapping("/{wishlistId}/product/{productId}")
    public ResponseEntity<Void> removeProductFromWishlist(@PathVariable Long wishlistId, @PathVariable Long productId) {
        wishlistService.removeProductFromWishlist(wishlistId, productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}