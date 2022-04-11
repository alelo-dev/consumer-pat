package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.models.Consumer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConsumerService {

    Consumer save(Consumer consumer);
    List<Consumer> findAll();
    Optional<Consumer> findById(UUID id);

}
