package br.com.alelo.consumer.consumerpat.domain.card.service;

import br.com.alelo.consumer.consumerpat.domain.card.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardBalance;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardType;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface CardService {
    void addCard(CardNumber cardNumber, CardType cardType, UUID consumerId);

    void updateCardBalance(final CardBalance cardBalance);

    Optional<CardBalance> searchCardBalanceByCardNumber(final CardNumber cardNumber);

    void chargeCard(final CardNumber cardNumber, final BigDecimal amount);

    Optional<Card> searchCardByCardNumber(final CardNumber cardNumber);
}
