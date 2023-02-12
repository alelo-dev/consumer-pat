package br.com.alelo.consumer.consumerpat.service.card;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.InsufficientBalanceException;
import br.com.alelo.consumer.consumerpat.exception.NotFoundException;

import java.math.BigDecimal;

public interface CardOperation {

    BigDecimal calculateDebitValue(BigDecimal value);

    Consumer debit(long cardNumber, BigDecimal value) throws NotFoundException, InsufficientBalanceException;

    Consumer credit(Consumer consumer, BigDecimal value) throws NotFoundException;

    CardTypeEnum getCardType();

}