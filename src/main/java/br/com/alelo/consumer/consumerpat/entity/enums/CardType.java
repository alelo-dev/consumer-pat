package br.com.alelo.consumer.consumerpat.entity.enums;

import java.math.BigDecimal;

public enum CardType implements CardBuyValueRule {
    FOOD_CARD {
        @Override
        public BigDecimal computeBuyValue(BigDecimal debitAmount) {
            return debitAmount.multiply(BigDecimal.valueOf(0.9));
        }
    },
    FUEL_CARD {
        @Override
        public BigDecimal computeBuyValue(BigDecimal debitAmount) {
            return debitAmount.multiply(BigDecimal.valueOf(1.35));
        }
    },
    DRUG_STORE
}

interface CardBuyValueRule {
    default BigDecimal computeBuyValue(BigDecimal debitAmount) {
        return debitAmount;
    }
}
