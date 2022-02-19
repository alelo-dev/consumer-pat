package br.com.alelo.consumer.consumerpat.entity.card;

import java.math.BigDecimal;

public interface ICard {
    BigDecimal calculateBalance(BigDecimal value);
}
