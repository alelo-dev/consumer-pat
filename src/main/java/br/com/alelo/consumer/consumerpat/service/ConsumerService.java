package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

import java.util.List;

public interface ConsumerService {

    public List<Consumer> getAllConsumersList();
    public void createConsumer(Consumer consumer);
    public void updateConsumer(Consumer consumer);
}
