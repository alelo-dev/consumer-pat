package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public interface ConsumerService {

    Consumer createConsumer(final Consumer consumer);

    Consumer updateConsumer(final Integer id);

}
