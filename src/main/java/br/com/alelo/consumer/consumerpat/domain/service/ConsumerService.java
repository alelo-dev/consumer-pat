package br.com.alelo.consumer.consumerpat.domain.service;

import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.enumeration.CardType;
import br.com.alelo.consumer.consumerpat.domain.exception.ConsumerApplicationException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Interface que define os serviços disponíveis para gerenciamento dos dadoso consumidor.
 */
public interface ConsumerService {

    List<Consumer> findAllConsumers();

    Consumer findConsumerById(BigInteger id) throws ConsumerApplicationException;

    void verifyIfConsumerAlreadyExists(Consumer consumer) throws ConsumerApplicationException;

    Consumer findConsumerByCardNumber(BigInteger cardNumber, CardType cardType) throws ConsumerApplicationException;

//    Consumer createConsumer(Consumer consumer) throws ConsumerApplicationException;

//    Consumer updateConsumer(Consumer consumer) throws ConsumerApplicationException;

    Consumer saveConsumer(Consumer consumer) throws ConsumerApplicationException;

    void deleteConsumer(BigInteger id) throws ConsumerApplicationException;

    Consumer setConsumersCardBalance(BigInteger cardNumber, BigDecimal value, CardType cardType) throws ConsumerApplicationException;
}