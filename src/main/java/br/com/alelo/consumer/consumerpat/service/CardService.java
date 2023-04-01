package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.request.SettlingRequest;
import br.com.alelo.consumer.consumerpat.response.CardResponse;

import java.math.BigDecimal;


public interface CardService {

    CardResponse updateBalance(String cardNumber, BigDecimal value);

    CardResponse updateSettlement(SettlingRequest settlingRequest);
}