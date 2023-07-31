package br.com.alelo.consumer.consumerpat.domain.consumer.service;

import br.com.alelo.consumer.consumerpat.domain.common.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.consumer.repository.DomainConsumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class DomainConsumerService implements ConsumerService {

    private final DomainConsumerRepository consumerRepository;

    public UUID createConsumer(final Consumer newConsumer) {
        return consumerRepository.save(newConsumer, true);
    }

    public void updateConsumer(final UUID consumerId, final Consumer updateConsumer) {
        var consumer = searchConsumerById(consumerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        format("Consumer [%s] not found", updateConsumer.getId())));

        consumer.changeConsumer(updateConsumer);

        consumerRepository.save(consumer, false);
    }

    public Optional<Consumer> searchConsumerById(final UUID consumerId) {
        return consumerRepository.findById(consumerId);
    }

    public Page<Consumer> listAll(Pageable consumerPageable) {
        return consumerRepository.listAll(consumerPageable);
    }
}
