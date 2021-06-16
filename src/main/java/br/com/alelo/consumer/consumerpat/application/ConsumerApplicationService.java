package br.com.alelo.consumer.consumerpat.application;

import br.com.alelo.consumer.consumerpat.domain.model.Consumer;
import br.com.alelo.consumer.consumerpat.enums.EstablishmentType;

import java.util.List;

public interface ConsumerApplicationService {

    List<Consumer> listAllConsumers();
    void createConsumer(Consumer consumer);
    void updateConsumer(Consumer consumer);
    void setBalance(int cardNumber, double value);
    void buy(EstablishmentType establishmentType, String establishmentName, int cardNumber, String productDescription, double value);

}
