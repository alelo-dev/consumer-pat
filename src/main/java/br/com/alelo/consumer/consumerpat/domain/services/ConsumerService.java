package br.com.alelo.consumer.consumerpat.domain.services;

import br.com.alelo.consumer.consumerpat.domain.entities.Consumer;
import br.com.alelo.consumer.consumerpat.domain.exceptions.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.exceptions.ConsumersNotFoundException;

import java.util.List;

public interface ConsumerService {

    List<Consumer> findAll(int page, int size) throws ConsumersNotFoundException;

    Consumer save(Consumer consumer);

    Consumer update(Consumer consumer) throws ConsumerNotFoundException;
}
