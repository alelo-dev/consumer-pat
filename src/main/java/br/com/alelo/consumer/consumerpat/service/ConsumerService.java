package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ConsumerService {

  private final ConsumerRepository consumerRepository;

  private final CardRepository cardRepository;

  public Page<Consumer> findAll(Pageable pageable) {
    return consumerRepository.findAll(pageable);
  }

  public void create(@RequestBody Consumer consumer) {
    throwIfExist(consumer);
    consumerRepository.save(consumer);
    consumer
        .getCards()
        .forEach(
            card -> {
              card.setConsumer(consumer);
              cardRepository.save(card);
            });
  }

  public ResponseEntity<Consumer> save(@RequestBody Consumer consumer) {
    return ResponseEntity.ok(consumerRepository.save(consumer));
  }

  public ResponseEntity<Consumer> update(Integer id, Consumer consumer) {
    if (consumerRepository.findById(id).isEmpty()) {
      throw new NoSuchElementException("Consumer: " + id + " not found");
    }
    consumerRepository
        .findById(id)
        .map(
            consumerUpdated -> {
              consumerUpdated.setName(consumer.getName());
              consumerUpdated.setDocumentNumber(consumer.getDocumentNumber());
              consumerUpdated.setBirthDate(consumer.getBirthDate());
              consumerUpdated.setContact(consumer.getContact());
              consumerUpdated.setAddress(consumer.getAddress());
              return ResponseEntity.ok().body(save(consumerUpdated));
            });
    return ResponseEntity.notFound().build();
  }

  private void throwIfExist(Consumer consumer) {
    if (consumerRepository.findConsumerByDocumentNumber(consumer.getDocumentNumber()).isPresent()) {
      throw new DataIntegrityViolationException("Consumer with same ID already exist");
    }
  }
}
