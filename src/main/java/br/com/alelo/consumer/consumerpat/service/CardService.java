package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.request.CardRequest;
import br.com.alelo.consumer.consumerpat.response.CardResponse;

import java.math.BigDecimal;

public interface CardService {

    CardResponse createCardForConsumer(CardRequest cardRequest);

    CardResponse addBalanceForCard(String cardNumber, BigDecimal cardBalance);

    CardResponse findCardNumber(String cardNumber);

    CardResponse activeCard(String cardNumber);
}
