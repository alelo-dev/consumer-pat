package br.com.alelo.consumer.consumerpat.domain.consumer.service;

import br.com.alelo.consumer.consumerpat.domain.common.DomainException;
import br.com.alelo.consumer.consumerpat.domain.common.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.consumer.repository.ConsumerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;

public class DomainConsumerService implements ConsumerService {

    private final ConsumerRepository consumerRepository;

    public DomainConsumerService(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    public UUID createConsumer(final Consumer newConsumer) {
        newConsumer.addId(UUID.randomUUID());
        consumerRepository.save(newConsumer);
        return newConsumer.getId();
    }

    public void updateConsumer(final UUID consumerId, final Consumer updateConsumer) {
        var consumer = searchConsumerById(consumerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        format("Consumer [%s] not found", updateConsumer.getId())));

        consumer.changeConsumer(updateConsumer);

        consumerRepository.save(consumer);
    }

    public Optional<Consumer> searchConsumerById(final UUID consumerId) {
        return consumerRepository.findById(consumerId);
    }

    public Page<Consumer> listAll(Pageable consumerPageable) {
        return null;
    }
}
