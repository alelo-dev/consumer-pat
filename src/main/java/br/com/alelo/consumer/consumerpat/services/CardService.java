package br.com.alelo.consumer.consumerpat.services;


import br.com.alelo.consumer.consumerpat.entity.Card;

import java.math.BigDecimal;

public interface CardService {


    Card increaseCardBalance(Integer cardNumber, BigDecimal amount);

    Card save(Card card);

    Card findOrFail(Long id);

    Card findByCardNumber(Integer number);

    Card decreaseBalance(Integer cardNumber, BigDecimal amount);
}
