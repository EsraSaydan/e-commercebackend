package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.converter.Converter;
import com.e_commerce.e_commerce.dto.response.CategoryResponse;
import com.e_commerce.e_commerce.entity.Category;
import com.e_commerce.e_commerce.exception.CommerceException;
import com.e_commerce.e_commerce.repository.CategoryRepository;
import com.e_commerce.e_commerce.validation.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return Converter.findCategories(categoryRepository.findAll());
    }

    @Override
    public Category getCategoryByID(long id) {
        EntityValidator.isValid(id, "category id ");
        EntityValidator.isCategoryIdValid("category id ", id);
        Category foundCategory = categoryRepository.getCategoryByID(id);
        if (foundCategory == null) {
            throw new CommerceException("Category not found!", HttpStatus.NOT_FOUND);
        }
        return foundCategory;
    }


    @Override
    public CategoryResponse save(Category category) {
        EntityValidator.checkEmptyOrNull(category.getCode(),"code ");
        EntityValidator.checkEmptyOrNull(category.getImg(),"img ");
        EntityValidator.checkEmptyOrNull(category.getTitle(),"title ");
        EntityValidator.isValid(category.getRating(),"rating ");
        Category saved = categoryRepository.save(category);
        return new CategoryResponse(category.getId(), category.getCode(), category.getTitle(),
                category.getImg(), category.getRating(), category.getGender());
    }
}
