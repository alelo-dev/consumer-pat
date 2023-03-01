package br.com.alelo.consumer.consumerpat.common.application.port.out;

import br.com.alelo.consumer.consumerpat.common.domain.Card;

import java.util.Optional;

public interface LoadCardPort {


    Optional<Card> findByNumber(String cardNumber);
}
