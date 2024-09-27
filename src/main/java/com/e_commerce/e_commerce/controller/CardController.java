package com.e_commerce.e_commerce.controller;

import com.e_commerce.e_commerce.dto.response.CartResponse;
import com.e_commerce.e_commerce.entity.Card;
import com.e_commerce.e_commerce.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;

    // Constructor-based dependency injection
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/create")
    public Card createCard(@RequestBody Card card) {
        return cardService.saveCard(card); // Statik olmayan method kullanımı
    }

    @GetMapping("/{id}")
    public Card getCard(@PathVariable long id) {
        return cardService.getCardById(id); // Statik olmayan method kullanımı
    }

    @GetMapping("/all")
    public List<Card> getAllCards() {
        return cardService.getAllCards(); // Statik olmayan method kullanımı
    }
}