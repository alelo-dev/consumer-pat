package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
        save(consumer);
    }

    private void save(Consumer consumer) {
        repository.save(consumer);
    }
}
