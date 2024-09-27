package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.dto.response.WishlistResponse;
import com.e_commerce.e_commerce.dto.request.WishlistRequest;
import com.e_commerce.e_commerce.entity.Wishlist;
import com.e_commerce.e_commerce.entity.Products;
import com.e_commerce.e_commerce.entity.User;
import com.e_commerce.e_commerce.exception.CommerceException;
import com.e_commerce.e_commerce.repository.WishlistRepository;
import com.e_commerce.e_commerce.repository.ProductRepository;
import com.e_commerce.e_commerce.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.wishlistRepository = wishlistRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<WishlistResponse> getWishlistByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommerceException("User not found", HttpStatus.NOT_FOUND));

        return wishlistRepository.findWishlistByUserId(userId).stream()
                .map(wishlist -> new WishlistResponse(wishlist.getId(), wishlist.getUser().getName(), wishlist.getProducts().size()))
                .collect(Collectors.toList());
    }

    @Override
    public WishlistResponse addProductToWishlist(WishlistRequest wishlistRequest) {
        User user = userRepository.findById(wishlistRequest.getUserId())
                .orElseThrow(() -> new CommerceException("User not found", HttpStatus.NOT_FOUND));

        Products product = productRepository.findById(wishlistRequest.getProductId())
                .orElseThrow(() -> new CommerceException("Product not found", HttpStatus.NOT_FOUND));

        // Kullanıcının mevcut bir wishlist'ini bul, yoksa yeni bir tane oluştur
        Wishlist wishlist = wishlistRepository.findWishlistByUserId(user.getId()).stream().findFirst().orElse(new Wishlist());
        wishlist.setUser(user);

        if (!wishlist.getProducts().contains(product)) {
            wishlist.getProducts().add(product); // Ürün wishlist'e eklenir
        } else {
            throw new CommerceException("Product already in wishlist", HttpStatus.BAD_REQUEST);
        }

        wishlistRepository.save(wishlist);

        return new WishlistResponse(wishlist.getId(), user.getName(), wishlist.getProducts().size());
    }
    @Override
    public WishlistResponse removeProductFromWishlist(Long wishlistId, Long productId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new CommerceException("Wishlist not found", HttpStatus.NOT_FOUND));

        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new CommerceException("Product not found", HttpStatus.NOT_FOUND));

        // Eğer products listesi immutable ise, yeni bir ArrayList ile değiştirilebilir hale getir
        if (wishlist.getProducts() == null) {
            throw new CommerceException("Product list is empty", HttpStatus.BAD_REQUEST);
        }

        wishlist.setProducts(new ArrayList<>(wishlist.getProducts()));

        if (wishlist.getProducts().contains(product)) {
            wishlist.getProducts().remove(product); // Ürün wishlist'ten çıkarılır
        } else {
            throw new CommerceException("Product not found in wishlist", HttpStatus.BAD_REQUEST);
        }

        wishlistRepository.save(wishlist);
        return new WishlistResponse(wishlist.getId(), wishlist.getUser().getName(), wishlist.getProducts().size());
    }


    @Override
    public void deleteWishlist(Long id) {
        if (!wishlistRepository.existsById(id)) {
            throw new CommerceException("Wishlist not found", HttpStatus.NOT_FOUND);
        }
        wishlistRepository.deleteById(id);
    }
}
