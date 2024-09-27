package com.e_commerce.e_commerce.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductResponseOrder{
    private long id;
    private int sellCount;
    private String description;
}