package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository repository;

    public List<Consumer> findAll() {
        List<Consumer> consumers = repository.findAll();
        return consumers;
    }

    public void create(Consumer consumer) {
        consumer.getCards().forEach(card -> card.setConsumer(consumer));
        save(consumer);
     }

    public void update(Consumer consumer) {
        consumer.getCards().forEach(card -> card.setConsumer(consumer));
        save(consumer);
    }

    private void save(Consumer consumer) {
        repository.save(consumer);
    }
}
