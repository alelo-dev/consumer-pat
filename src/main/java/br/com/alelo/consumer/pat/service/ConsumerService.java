package br.com.alelo.consumer.pat.service;

import br.com.alelo.consumer.pat.entity.Consumer;
import br.com.alelo.consumer.pat.exception.ConsumerNotFoundException;
import br.com.alelo.consumer.pat.payload.ConsumerPayload;
import br.com.alelo.consumer.pat.payload.CreateConsumerPayload;
import br.com.alelo.consumer.pat.respository.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final ConsumerRepository repository;

    public Page<ConsumerPayload> findAllPaginated(Pageable pageable) {
        Page<Consumer> consumers = repository.findAll(pageable);

        return consumers.map(ConsumerPayload::from);
    }

    public void createConsumer(final CreateConsumerPayload consumerPayload) {
        Consumer consumer = Consumer.from(consumerPayload);

        repository.save(consumer);
    }

    public void updateConsumer(final Long consumerId, final CreateConsumerPayload consumerPayload) {
        Consumer consumer = repository.findById(consumerId).orElseThrow(ConsumerNotFoundException::new);

        consumer.setName(consumerPayload.getName());
        consumer.setDocumentNumber(consumerPayload.getDocumentNumber());
        consumer.setBirthDate(consumerPayload.getBirthDate());
        consumer.setMobilePhoneNumber(consumerPayload.getMobilePhoneNumber());
        consumer.setResidencePhoneNumber(consumerPayload.getResidencePhoneNumber());
        consumer.setPhoneNumber(consumerPayload.getPhoneNumber());
        consumer.setEmail(consumerPayload.getEmail());
        consumer.setStreet(consumerPayload.getStreet());
        consumer.setNumber(consumerPayload.getNumber());
        consumer.setCity(consumerPayload.getCity());
        consumer.setCountry(consumerPayload.getCountry());
        consumer.setPortalCode(consumerPayload.getPortalCode());

        repository.save(consumer);
    }

}
