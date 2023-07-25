package br.com.alelo.consumer.consumerpat.domain.consumer.service;

import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Address;
import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Contact;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface ConsumerService {
    UUID createConsumer(String name, String documentNumber, LocalDate birthDate, Contact contact, Address address);

    void updateConsumer(Consumer updateConsumer);

    Optional<Consumer> searchConsumerById(final UUID consumerId);
}
