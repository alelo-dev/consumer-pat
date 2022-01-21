package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    ConsumerRepository consumerRepository;

    @Override
    public Consumer createConsumer(Consumer consumer) {
        return consumerRepository.save(consumer);
    }
}
