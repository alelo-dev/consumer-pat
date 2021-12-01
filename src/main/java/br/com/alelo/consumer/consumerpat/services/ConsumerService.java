package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

import java.util.List;

public interface ConsumerService {

    List<Consumer> listAllConsumers();

    Consumer createConsumer(final Consumer consumer);

    Consumer updateConsumer(final Consumer consumer);

    void setCardBalance(final int cardNumber, final double value);

    void buy(final int establishmentType, final String establishmentName, final int cardNumber,
             final String productDescription, double value);

}
