package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@Service
public class ConsumerService {

    private final ConsumerRepository repository;

    @Autowired
    public ConsumerService(ConsumerRepository repository) {
        this.repository = repository;
    }
    
    public List<Consumer> findAll() {
        return this.repository.findAll();
    }

    public Consumer createAddress(Consumer entity) {
        return this.repository.save(entity);
    }

    public Consumer updateAddress(Consumer consumer, Integer consumerId) {
        final var updatedConsumer = this.repository.findById(consumerId)
            .map(entity -> {
                entity.setBirthDate(consumer.getBirthDate());
                entity.setDocumentNumber(consumer.getDocumentNumber());
                entity.setName(consumer.getName());
                return this.repository.save(entity);
            }).orElseThrow();

        return updatedConsumer;
    }
}
