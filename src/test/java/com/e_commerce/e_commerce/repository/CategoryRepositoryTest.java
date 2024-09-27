package com.e_commerce.e_commerce.repository;

import com.e_commerce.e_commerce.entity.Category;
import com.e_commerce.e_commerce.entity.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void saveCategoryTest() {
        // Yeni kategori oluştur
        Category category = new Category();
        category.setCode("C999");
        category.setTitle("Test Category");
        category.setImg("test_image.png");
        category.setRating(5.0);
        category.setGender(Gender.MALE);

        // Kategoriyi kaydet
        Category savedCategory = categoryRepository.save(category);

        // Kaydedilen kategorinin null olmadığını kontrol et
        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getId()).isGreaterThan(0);
    }

    @Test
    void findCategoryByIdTest() {
        // Kategori oluştur ve kaydet
        Category category = new Category();
        category.setCode("C123");
        category.setTitle("Books");
        category.setImg("books.png");
        category.setRating(4.5);
        category.setGender(Gender.MALE);

        categoryRepository.save(category);

        // Kaydedilen kategoriyi id ile bul
        Category foundCategory = categoryRepository.getCategoryByID(category.getId());

        // Doğru kategori döndüğünü kontrol et
        assertThat(foundCategory).isNotNull();
        assertThat(foundCategory.getTitle()).isEqualTo("Books");
    }
}
