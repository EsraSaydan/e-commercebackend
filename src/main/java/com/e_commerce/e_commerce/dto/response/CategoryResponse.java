package com.e_commerce.e_commerce.dto.response;

import com.e_commerce.e_commerce.entity.Gender;


public record CategoryResponse(Long id,String code,String title, String img,Double rating, Gender gender){
}