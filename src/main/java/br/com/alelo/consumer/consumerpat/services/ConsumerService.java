package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository repository;

    // Método que busca um consumer por id
    public Consumer findById(Integer id) {
        Optional<Consumer> obj = repository.findById(id);
        return obj.orElse(null);
    }

    // Lista todos os consumers
    public List<Consumer> findAll() {
        return repository.findAll();
    }
}
