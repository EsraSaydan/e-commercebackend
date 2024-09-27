package com.e_commerce.e_commerce.controller;

import com.e_commerce.e_commerce.dto.response.CategoryResponse;
import com.e_commerce.e_commerce.entity.Category;
import com.e_commerce.e_commerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/")
    public List<CategoryResponse> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/")
    public CategoryResponse save(@RequestBody Category category) {
        return categoryService.save(category);
    }

}