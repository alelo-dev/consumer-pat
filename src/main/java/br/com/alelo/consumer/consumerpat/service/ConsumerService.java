package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository repository;

    public List<Consumer> findAll() {
        return repository.findAll();
    }

    public void create(Consumer consumer) {
        save(consumer);
    }

    public void update(Consumer consumer) {
        save(consumer);
    }

    private void save(Consumer consumer) {
        repository.save(consumer);
    }
}
