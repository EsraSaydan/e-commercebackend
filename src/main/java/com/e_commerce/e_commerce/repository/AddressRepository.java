package com.e_commerce.e_commerce.repository;

import com.e_commerce.e_commerce.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)  // Okuma işlemleri için optimize edilmiştir
public interface AddressRepository extends JpaRepository<Address, Long> {



    // Bu sorgu, Address tablosundan belirli bir id'ye sahip adresi sorgulamak için kullanılır.
// @Query ile native SQL sorgusu yazılmıştır ve nativeQuery = true olduğu için
// doğrudan SQL sorgusu olarak çalıştırılır.
// Bu sorgu sonucunda Optional<Address> döndürülür, yani belirtilen id'ye sahip bir adres
// varsa döndürülür, aksi takdirde boş bir değer dönebilir.
    @Query(value = "SELECT * FROM address AS a WHERE a.id = :id", nativeQuery = true)
    Optional<Address> findAddressByID(long id);
}
