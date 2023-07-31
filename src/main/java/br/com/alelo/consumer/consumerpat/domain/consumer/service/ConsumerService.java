package br.com.alelo.consumer.consumerpat.domain.consumer.service;

import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Consumer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ConsumerService {
    UUID createConsumer(final Consumer newConsumer);

    void updateConsumer(final UUID consumerId, final Consumer updateConsumer);

    Optional<Consumer> searchConsumerById(final UUID consumerId);

    Page<Consumer> listAll(Pageable consumerPageable);
}
