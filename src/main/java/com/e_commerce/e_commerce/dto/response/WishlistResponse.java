package com.e_commerce.e_commerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WishlistResponse {
    private Long id;
    private String name;
    private int productCount;
}
