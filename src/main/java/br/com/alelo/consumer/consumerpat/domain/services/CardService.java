package br.com.alelo.consumer.consumerpat.domain.services;

import br.com.alelo.consumer.consumerpat.domain.dto.CardBalance;
import br.com.alelo.consumer.consumerpat.domain.dto.DebitCard;
import br.com.alelo.consumer.consumerpat.domain.exceptions.CardNotFoundException;

public interface CardService {

    void addBalance(CardBalance cardBalance) throws CardNotFoundException;

    void buy(DebitCard debitCard) throws CardNotFoundException;
}
