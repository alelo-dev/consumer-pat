package br.com.alelo.consumer.consumerpat.recharge.domain;

import br.com.alelo.consumer.consumerpat.common.domain.Card;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value(staticConstructor = "of")
public class Recharge {

    Card card;
    BigDecimal amount;
    LocalDate rechargedAt;
}
