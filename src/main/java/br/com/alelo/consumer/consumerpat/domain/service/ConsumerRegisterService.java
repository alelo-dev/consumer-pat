package br.com.alelo.consumer.consumerpat.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.domain.exception.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.model.Consumer;
import br.com.alelo.consumer.consumerpat.domain.repository.ConsumerRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ConsumerRegisterService {

    final ConsumerRepository repository;

    public void save(Consumer consumer) {
        repository.save(consumer);
    }

    public Consumer findById(Long consumerId) {
        return repository.findById(consumerId).orElseThrow(
                () -> new ConsumerNotFoundException(String.format("Consumer with id %d not found.", consumerId)));
    }

    public List<Consumer> getConsumers(Pageable pageable) {
        Page<Consumer> findAll = repository.findAll(pageable);
        return findAll.getContent();
    }
}
