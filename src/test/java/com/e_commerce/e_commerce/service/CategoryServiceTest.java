package com.e_commerce.e_commerce.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.e_commerce.e_commerce.dto.response.CategoryResponse;
import com.e_commerce.e_commerce.entity.Category;
import com.e_commerce.e_commerce.exception.CommerceException;
import com.e_commerce.e_commerce.repository.CategoryRepository;
import com.e_commerce.e_commerce.service.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Optional;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCategoryByID_Success() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setCode("C123");
        category.setTitle("Books");
        when(categoryRepository.getCategoryByID(1L)).thenReturn(category);

        // Act
        Category result = categoryService.getCategoryByID(1L);

        // Assert
        assertEquals("Books", result.getTitle());
        verify(categoryRepository, times(1)).getCategoryByID(1L);
    }

    @Test
    void testGetCategoryByID_NotFound() {
        // Arrange
        when(categoryRepository.getCategoryByID(1L)).thenReturn(null);

        // Act & Assert
        CommerceException exception = assertThrows(CommerceException.class, () -> {
            categoryService.getCategoryByID(1L);
        });
        assertEquals("Category not found!", exception.getMessage());
    }

    @Test
    void testSaveCategory_Success() {
        // Arrange
        Category category = new Category();
        category.setCode("C124");
        category.setTitle("Electronics");
        category.setImg("image.png");
        category.setRating(4.5);

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        // Act
        CategoryResponse savedCategory = categoryService.save(category);

        // Assert
        assertEquals("Electronics", savedCategory.title());
        verify(categoryRepository, times(1)).save(category);
    }
}
