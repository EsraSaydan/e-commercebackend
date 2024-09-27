package com.e_commerce.e_commerce.service;


import com.e_commerce.e_commerce.entity.Card;

import java.util.List;

public interface CardService {
    List<Card> getAllCards();
    Card getCardById(long id);
    Card saveCard(Card card);
}
