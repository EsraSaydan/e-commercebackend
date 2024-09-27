package com.e_commerce.e_commerce.controller;

import com.e_commerce.e_commerce.controller.ProductController;
import com.e_commerce.e_commerce.dto.response.ProductResponse;
import com.e_commerce.e_commerce.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}) // Mock kullanıcıyı tanımlıyoruz
    public void shouldReturnProductList() throws Exception {
        // Simüle edilen ProductResponse listesi
        List<ProductResponse> products = Arrays.asList(
                new ProductResponse(1L, "Yeni Ürün", "Bu bir test ürünü açıklamasıdır.", "99.99", "100", 1L, "5", "0", "https://example.com/image.png"),
                new ProductResponse(2L, "Kazak", "siyah", "200", "20", 4L, "3", "80", "https://example.com/image.png"),
                new ProductResponse(3L, "Elbise", "size L", "200", "10", 5L, "3", "60", "URL")
        );
        when(productService.getAllProductResponses()).thenReturn(products);

        // GET isteği gönder ve sonucu kontrol et
        mockMvc.perform(get("/products/all"))
                .andExpect(status().isOk())  // HTTP 200 kontrolü
                .andExpect(jsonPath("$[0].name").value("Yeni Ürün"))  // İlk ürün adı kontrolü
                .andExpect(jsonPath("$[0].price").value("99.99"))     // İlk ürün fiyatı kontrolü
                .andExpect(jsonPath("$[1].name").value("Kazak"))      // İkinci ürün adı kontrolü
                .andExpect(jsonPath("$[1].price").value("200"))       // İkinci ürün fiyatı kontrolü
                .andExpect(jsonPath("$[2].name").value("Elbise"))     // Üçüncü ürün adı kontrolü
                .andExpect(jsonPath("$[2].price").value("200"));      // Üçüncü ürün fiyatı kontrolü
    }
}
