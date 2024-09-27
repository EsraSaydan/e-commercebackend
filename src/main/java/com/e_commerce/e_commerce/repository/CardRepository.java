package com.e_commerce.e_commerce.repository;

import com.e_commerce.e_commerce.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)  // Okuma işlemleri için optimize edilmiştir
public interface CardRepository extends JpaRepository<Card, Long> {

    // Belirtilen id'ye göre Card tablosundan kartı getiren SQL sorgusu
    @Query(value = "SELECT * FROM card AS c WHERE c.id = :cardId", nativeQuery = true)
    Card getCardById(long cardId);
}
