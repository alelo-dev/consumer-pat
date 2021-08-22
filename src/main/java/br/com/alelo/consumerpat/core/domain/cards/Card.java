package br.com.alelo.consumerpat.core.domain.cards;

import java.math.BigDecimal;

public interface Card {

    BigDecimal calculateBalance(BigDecimal balance, BigDecimal value);
}
