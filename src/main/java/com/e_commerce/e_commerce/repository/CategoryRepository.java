package com.e_commerce.e_commerce.repository;

import com.e_commerce.e_commerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)  // Okuma işlemleri için optimize edilmiştir
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Veritabanından belirtilen id'ye sahip kategori getirilir
    @Query(value = "SELECT * FROM categories AS c WHERE c.id = :categoryID", nativeQuery = true)
    Category getCategoryByID(long categoryID);
}
