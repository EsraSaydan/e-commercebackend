package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.dto.request.ProductRequest;
import com.e_commerce.e_commerce.dto.response.ProductResponse;
import com.e_commerce.e_commerce.entity.Products;

import java.util.List;

public interface ProductService {

    // Tüm ürünleri listele
    List<ProductResponse> getAllProductResponses();

    // Ürün ID ile bul
    ProductResponse getProductById(long id);

    // Yeni ürün ekle
    ProductResponse saveProduct(ProductRequest productRequest);

    // Mevcut ürünü güncelle
    ProductResponse updateProduct(long id, ProductRequest productRequest);

    // Ürün sil
    void deleteProduct(long id);
}
