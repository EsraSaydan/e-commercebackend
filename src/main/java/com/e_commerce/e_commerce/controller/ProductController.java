package com.e_commerce.e_commerce.controller;

import com.e_commerce.e_commerce.dto.request.ProductRequest;
import com.e_commerce.e_commerce.dto.response.ProductResponse;
import com.e_commerce.e_commerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Tüm ürünleri listele
    @GetMapping("/all")
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProductResponses();
    }

    // ID ile ürünü getir
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }

    // Yeni ürün ekle
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@Valid @RequestBody ProductRequest productRequest) {
        return productService.saveProduct(productRequest);
    }

    // Ürün güncelle
    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable long id, @Valid @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(id, productRequest);
    }

    // Ürün sil
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }
}