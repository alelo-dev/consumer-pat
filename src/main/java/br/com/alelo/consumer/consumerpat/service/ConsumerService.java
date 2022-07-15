package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço relativo ao gerenciamento dos dados do cliente.
 */
@Service
public class ConsumerService {

    private ConsumerRepository consumerRepository;

    @Autowired
    public ConsumerService(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    public ConsumerRepository getConsumerRepository() {
        return this.consumerRepository;
    }

    public List<Consumer> findAll() {
        return (List) this.consumerRepository.findAll();
    }

    public Consumer findById(Long id) throws ResourceNotFoundException {
        return this.consumerRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public void save(Consumer consumer, Long id) throws ResourceNotFoundException {
        if (id != null) {
            Consumer fetchedConsumer = this.findById(id);
            consumer.setId(fetchedConsumer.getId());
            this.consumerRepository.save(consumer);
        }
    }

    public void save(Consumer consumer) {
        this.consumerRepository.save(consumer);
    }

    public void delete(Long id) {
        this.consumerRepository.deleteById(id);
    }

}
