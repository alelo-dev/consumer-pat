package br.com.alelo.consumer.consumerpat.domain.repositories;

import br.com.alelo.consumer.consumerpat.domain.entities.Card;

import java.util.Optional;

public interface CardRepository {

    Optional<Card> findByCardNumberAndConsumerId(String cardNumber, int consumerId);

    void updateBalance(Card card);
}
