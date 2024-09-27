package com.e_commerce.e_commerce.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {
    private Long productId;
    private Long userId;
    private String comment;
    private int rating;
}
