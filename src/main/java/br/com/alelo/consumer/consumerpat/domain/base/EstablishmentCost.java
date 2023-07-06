package br.com.alelo.consumer.consumerpat.domain.base;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

import java.math.BigDecimal;

public interface EstablishmentCost {

    Consumer updateBalance(BigDecimal value, Integer cardNumber, Consumer consumer);

}
