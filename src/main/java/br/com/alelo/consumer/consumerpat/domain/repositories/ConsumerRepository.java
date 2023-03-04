package br.com.alelo.consumer.consumerpat.domain.repositories;

import br.com.alelo.consumer.consumerpat.domain.entities.Consumer;

import java.util.List;

public interface ConsumerRepository {

    List<Consumer> findAll(int page, int size);

    Boolean findById(int id);

    Consumer save(Consumer consumer);

    Consumer update(Consumer consumer);
}
