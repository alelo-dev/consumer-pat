package br.com.alelo.consumer.consumerpat.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelo.consumer.consumerpat.exception.NotFoundException;
import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConsumerService {
    
    private final ConsumerRepository consumerRepository;
    
    @Transactional(readOnly = true)
    public Page<Consumer> findAll(final Pageable pageable) {
        return consumerRepository.findAll(pageable);
    }
    
    @Transactional
    public Consumer save(final Consumer consumer) {
        return consumerRepository.save(consumer);
    }

    @Transactional
    public void update(final Consumer consumer) {
        var c = consumerRepository.findById(consumer.getId())
                .orElseThrow(() -> new NotFoundException("Consumer not found."));

        c.merge(consumer);
    }
    
}
