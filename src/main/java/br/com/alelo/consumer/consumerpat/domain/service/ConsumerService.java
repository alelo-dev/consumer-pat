package br.com.alelo.consumer.consumerpat.domain.service;

import br.com.alelo.consumer.consumerpat.domain.model.Consumer;
import br.com.alelo.consumer.consumerpat.enums.EstablishmentType;

import java.util.List;

public interface ConsumerService {
    void setBalance(int cardNumber, double value);

    void save(Consumer consumer);

    List<Consumer> listAllConsumers();

    double buy(EstablishmentType establishmentType, String establishmentName, int cardNumber, String productDescription, double value);
}
