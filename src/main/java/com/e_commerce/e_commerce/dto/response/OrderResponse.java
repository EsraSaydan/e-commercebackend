package com.e_commerce.e_commerce.dto.response;


import com.e_commerce.e_commerce.dto.request.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse{
    private long id;

    private long addressId;

    private String orderDate;

    private String cardNumber;

    private String cardMonth;

    private String cardYear;

    private String cardCvv;

    private String price;

    private String userName;

    private List<ProductRequest> products;
}