package com.e_commerce.e_commerce.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private long id; // Bu alan null olabilir çünkü ekleme işlemlerinde kullanmayacağız

    @NotBlank(message = "Product name cannot be blank")
    private String name;

    @NotNull(message = "Price cannot be null")
    private String price;

    @Min(value = 0, message = "Stock should be at least 0")
    private String stock;

    @NotNull(message = "CategoryId cannot be null")
    private long categoryId;

    private int sellCount;

    private String description;

    private String image;

    private String rating;
}
