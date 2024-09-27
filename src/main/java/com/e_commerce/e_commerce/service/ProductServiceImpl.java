package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.dto.request.ProductRequest;
import com.e_commerce.e_commerce.dto.response.ProductResponse;
import com.e_commerce.e_commerce.entity.Products;
import com.e_commerce.e_commerce.repository.ProductRepository;
import com.e_commerce.e_commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Tüm ürünleri listele
    @Override
    public List<ProductResponse> getAllProductResponses() {
        List<Products> products = productRepository.findAll();
        return products.stream().map(this::convertToProductResponse).collect(Collectors.toList());
    }

    // ID ile ürün bul
    @Override
    public ProductResponse getProductById(long id) {
        Products product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return convertToProductResponse(product);
    }

    // Yeni ürün ekle
    @Override
    @Transactional
    public ProductResponse saveProduct(ProductRequest productRequest) {
        Products product = new Products();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setCategoryId(productRequest.getCategoryId());
        product.setImage(productRequest.getImage());
        product.setSellCount(String.valueOf(productRequest.getSellCount()));
        product.setRating(productRequest.getRating());
        return convertToProductResponse(productRepository.save(product));
    }

    // Ürün güncelle
    @Override
    @Transactional
    public ProductResponse updateProduct(long id, ProductRequest productRequest) {
        Products product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setCategoryId(productRequest.getCategoryId());
        product.setImage(productRequest.getImage());
        product.setSellCount(String.valueOf(productRequest.getSellCount()));
        product.setRating(productRequest.getRating());
        return convertToProductResponse(productRepository.save(product));
    }

    // Ürün sil
    @Override
    @Transactional
    public void deleteProduct(long id) {
        Products product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }

    // Products entity'sini ProductResponse'e dönüştürme
    private ProductResponse convertToProductResponse(Products product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategoryId(),
                product.getRating(),
                product.getSellCount(),
                product.getImage()
        );
    }
}
