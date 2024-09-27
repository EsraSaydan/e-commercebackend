package com.e_commerce.e_commerce.dto.response;

public record ProductResponse(Long id, String name, String description, String price, String stock, Long categoryID,
                              String rating, String sellCount, String image) {
}
