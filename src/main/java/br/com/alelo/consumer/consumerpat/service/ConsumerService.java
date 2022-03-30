package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

import java.util.List;

public interface ConsumerService {

    List<Consumer> listAllConsumers();
    void createConsumer(Consumer consumer);
    void updateConsumer(Consumer consumer);

}
