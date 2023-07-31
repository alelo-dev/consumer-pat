package br.com.alelo.consumer.consumerpat.domain.consumer.repository;

import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Consumer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface DomainConsumerRepository {

    Optional<Consumer> findById(UUID id);
    UUID save(final Consumer consumer, final boolean isNew);

    Page<Consumer> listAll(Pageable consumerPageable);
}
