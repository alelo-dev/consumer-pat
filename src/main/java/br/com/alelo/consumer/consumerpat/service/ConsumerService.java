package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private CardRepository cardRepository;

    public Page<Consumer> findAll(Pageable pageable) {
        return consumerRepository.findAll(pageable);
    }

    public void create(@RequestBody Consumer consumer) {
        exists(consumer);
        consumerRepository.save(consumer);
        for (Card card : consumer.getCards()) {
            card.setConsumer(consumer);
            cardRepository.save(card);
        }
    }

    public ResponseEntity<Consumer> save(@RequestBody Consumer consumer) {
        return ResponseEntity.ok(consumerRepository.save(consumer));
    }

    public Optional<Consumer> findById(Integer id) {
        return consumerRepository.findById(id);
    }

    public ResponseEntity<Consumer> update(Integer id, Consumer consumer) {
        if (findById(id).isEmpty()) {
            throw new NoSuchElementException("Consumer not found!");
        }
        findById(id).map(consumerUpdated -> {
            consumerUpdated.setName(consumer.getName());
            consumerUpdated.setDocumentNumber(consumer.getDocumentNumber());
            consumerUpdated.setBirthDate(consumer.getBirthDate());
            consumerUpdated.setContact(consumer.getContact());
            consumerUpdated.setAddress(consumer.getAddress());
            return ResponseEntity.ok().body(save(consumerUpdated));
        });
        return ResponseEntity.notFound().build();
    }

    private void exists(Consumer consumer) {
        if (consumerRepository.findConsumerByDocumentNumber(consumer.getDocumentNumber()).isPresent()) {
            throw new DataIntegrityViolationException("There already exists an user with this document number in the database.");
        }
    }
}
