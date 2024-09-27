package com.e_commerce.e_commerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewResponse {
    private Long id;
    private String comment;
    private int rating;
    private String productName;
}
