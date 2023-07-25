package br.com.alelo.consumer.consumerpat.domain.consumer.repository;

import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Consumer;

import java.util.Optional;
import java.util.UUID;

public interface ConsumerRepository {

    Optional<Consumer> findById(UUID id);
    void save(final Consumer consumer);
}
