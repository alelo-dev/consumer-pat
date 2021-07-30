package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.enumerated.CardType;

import java.math.BigDecimal;

public interface TransactionOperation {
    BigDecimal calculate(BigDecimal value);

    CardType getCardType();
}
