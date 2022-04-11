package br.com.alelo.consumer.consumerpat.services.impl;

import br.com.alelo.consumer.consumerpat.models.Consumer;
import br.com.alelo.consumer.consumerpat.repositories.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.services.ConsumerService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ConsumerServiceImpl implements ConsumerService {

    final ConsumerRepository consumerRepository;

    public ConsumerServiceImpl(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    @Transactional
    public Consumer save(Consumer consumer) {
        return consumerRepository.save(consumer);
    }

    public List<Consumer> findAll() {
        return consumerRepository.findAll();
    }

    public Optional<Consumer> findById(UUID id) {
        return consumerRepository.findById(id);
    }
}
