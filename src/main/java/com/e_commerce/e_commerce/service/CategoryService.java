package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.dto.response.CategoryResponse;
import com.e_commerce.e_commerce.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategories();
    Category getCategoryByID(long id);
    CategoryResponse save(Category category);

}
