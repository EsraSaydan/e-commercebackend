package com.e_commerce.e_commerce.repository;

import com.e_commerce.e_commerce.entity.Order;
import com.e_commerce.e_commerce.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    private Order testOrder;

    @BeforeEach
    void setUp() {
        // Test Order nesnesini başlatıyoruz
        User user = new User(); // User nesnesini uygun şekilde doldurun
        user.setId(1L); // Örnek olarak, ID setliyoruz

        testOrder = new Order();
        testOrder.setOrderDate("2024-09-01");
        testOrder.setCardNumber("1234567812345678");
        testOrder.setCardMonth("12");
        testOrder.setCardYear("2024");
        testOrder.setCardCvv("123");
        testOrder.setPrice("500");
        testOrder.setUserName("esra@example.com");
        testOrder.setUser(user); // Kullanıcı setleniyor
        // Diğer alanlar istenilen şekilde doldurulabilir
    }

    @Test
    void testSaveOrder() {
        // Test order nesnesini kaydediyoruz
        Order savedOrder = orderRepository.save(testOrder);

        // Kaydedilen veriyi kontrol ediyoruz
        assertNotNull(savedOrder.getId());
        assertEquals(testOrder.getOrderDate(), savedOrder.getOrderDate());
        assertEquals(testOrder.getCardNumber(), savedOrder.getCardNumber());
        assertEquals(testOrder.getPrice(), savedOrder.getPrice());
    }

    @Test
    void testFindById() {
        // Test order nesnesini kaydediyoruz
        Order savedOrder = orderRepository.save(testOrder);

        // ID ile sorgulama yapıyoruz
        Optional<Order> foundOrder = orderRepository.findById(savedOrder.getId());

        // Sorgulanan veriyi kontrol ediyoruz
        assertTrue(foundOrder.isPresent());
        assertEquals(savedOrder.getId(), foundOrder.get().getId());
    }

    @Test
    void testDeleteOrder() {
        // Test order nesnesini kaydediyoruz
        Order savedOrder = orderRepository.save(testOrder);

        // Order siliniyor
        orderRepository.delete(savedOrder);

        // Order'ın silindiğini kontrol ediyoruz
        Optional<Order> foundOrder = orderRepository.findById(savedOrder.getId());
        assertFalse(foundOrder.isPresent());
    }
}
