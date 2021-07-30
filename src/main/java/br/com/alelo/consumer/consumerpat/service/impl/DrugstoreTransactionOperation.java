package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.enumerated.CardType;
import br.com.alelo.consumer.consumerpat.service.TransactionOperation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class DrugstoreTransactionOperation implements TransactionOperation {

    @Override
    public BigDecimal calculate(BigDecimal value) {
        return value.setScale(2, RoundingMode.FLOOR);
    }

    @Override
    public CardType getCardType() {
        return CardType.DRUGSTORE_CARD;
    }
}
