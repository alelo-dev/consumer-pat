package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

import java.util.List;

public interface ConsumerService {

    List<Consumer> listAllConsumers();

    void createConsumer(Consumer consumer); //TODO set as final

    void updateConsumer(Consumer consumer); //TODO set as final

    void setBalance(final int cardNumber, final double value);

    void buy(final int establishmentType, final String establishmentName, final int cardNumber,
             final String productDescription, double value);

}
