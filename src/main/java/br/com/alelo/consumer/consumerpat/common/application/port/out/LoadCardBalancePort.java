package br.com.alelo.consumer.consumerpat.common.application.port.out;

import br.com.alelo.consumer.consumerpat.common.domain.Card;
import br.com.alelo.consumer.consumerpat.common.domain.CardBalance;

import java.time.LocalDate;

public interface LoadCardBalancePort {

    CardBalance calculate(Card card, LocalDate beginDate, LocalDate endDate);
}
