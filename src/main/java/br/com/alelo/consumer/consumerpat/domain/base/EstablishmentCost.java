package br.com.alelo.consumer.consumerpat.domain.base;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public interface EstablishmentCost {

    Consumer updateBalance(Double value, Integer cardNumber, Consumer consumer);

}
