package br.com.alelo.consumer.consumerpat.domain.consumer.service;

import br.com.alelo.consumer.consumerpat.domain.common.DomainException;
import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Address;
import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Contact;
import br.com.alelo.consumer.consumerpat.domain.consumer.repository.ConsumerRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;

public class DomainConsumerService implements ConsumerService {

    private final ConsumerRepository consumerRepository;

    public DomainConsumerService(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    public UUID createConsumer(
            String name,
            String documentNumber,
            LocalDate birthDate,
            Contact contact,
            Address address) {
        final var consumer = new Consumer(UUID.randomUUID(), name, documentNumber, birthDate);
        consumer.addContact(contact);
        consumer.addAddress(address);

        consumerRepository.save(consumer);

        return consumer.getId();
    }

    public void updateConsumer(Consumer updateConsumer) {
        var consumer = searchConsumerById(updateConsumer.getId())
                .orElseThrow(() -> new DomainException(format("Consumer [%s] not found", updateConsumer.getId())));

        consumer.changeConsumer(updateConsumer);

        consumerRepository.save(consumer);
    }

    public Optional<Consumer> searchConsumerById(final UUID consumerId) {
        return consumerRepository.findById(consumerId);
    }
}
