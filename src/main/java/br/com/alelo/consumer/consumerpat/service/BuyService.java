package br.com.alelo.consumer.consumerpat.service;

import java.math.BigDecimal;

public interface BuyService {
    void buy(Long cardNumber, BigDecimal value) throws Exception;
}
