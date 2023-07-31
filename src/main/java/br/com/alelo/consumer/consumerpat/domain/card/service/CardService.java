package br.com.alelo.consumer.consumerpat.domain.card.service;

import br.com.alelo.consumer.consumerpat.domain.card.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardBalance;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CardService {
    void addCard(final UUID consumerId, final Card newCard);

    void updateCard(final Card card);

    Optional<CardBalance> searchCardBalanceByCardNumber(final CardNumber cardNumber);

    void chargeCard(final CardNumber cardNumber, final BigDecimal amount);

    Optional<Card> searchCardByCardNumber(final CardNumber cardNumber);

    Optional<Set<Card>> searchCardByConsumerId(final UUID consumerId);
}
