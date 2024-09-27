package com.e_commerce.e_commerce.repository;

import com.e_commerce.e_commerce.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Products, Long> {

    // Ürün ismine göre arama, ILIKE kullanımıyla case-insensitive
    @Query("SELECT p FROM Products p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :productName, '%'))")
    List<Products> findByProductName(String productName);

    // Kategoriye göre ürün bulma
    @Query("SELECT p FROM Products p WHERE p.categoryId = :categoryID")
    List<Products> getProductsByCategoryId(long categoryID);

    // İd ile ürün bulma
    @Query("SELECT p FROM Products p WHERE p.id = :id")
    Optional<Products> getProductById(long id);

    // Kategori ve isme göre ürün arama, ILIKE kullanımıyla
    @Query("SELECT p FROM Products p WHERE p.categoryId = :categoryID AND LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Products> searchByNameAndCategory(String name, long categoryID);

    // Fiyat sıralama (en yüksekten en düşüğe)
    @Query("SELECT p FROM Products p ORDER BY p.price DESC")
    List<Products> highestToLowestSorting();

    // Fiyat sıralama (en düşükten en yükseğe)
    @Query("SELECT p FROM Products p ORDER BY p.price ASC")
    List<Products> lowestToHighestSorting();

    // Kategoriye göre fiyat sıralama (en yüksekten en düşüğe)
    @Query("SELECT p FROM Products p WHERE p.categoryId = :categoryID ORDER BY p.price DESC")
    List<Products> highestToLowestSortingAndCategory(long categoryID);

    // Kategoriye göre fiyat sıralama (en düşükten en yükseğe)
    @Query("SELECT p FROM Products p WHERE p.categoryId = :categoryID ORDER BY p.price ASC")
    List<Products> lowestToHighestSortingAndCategory(long categoryID);

    // Rating sıralama (en yüksekten en düşüğe)
    @Query("SELECT p FROM Products p ORDER BY p.rating DESC")
    List<Products> bestToWorstSorting();

    // Rating sıralama (en düşükten en yükseğe)
    @Query("SELECT p FROM Products p ORDER BY p.rating ASC")
    List<Products> worstToBestSorting();
}
