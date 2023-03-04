package br.com.alelo.consumer.consumerpat.domain.services.impl;

import br.com.alelo.consumer.consumerpat.domain.entities.Consumer;
import br.com.alelo.consumer.consumerpat.domain.exceptions.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.exceptions.ConsumersNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.exceptions.ErrorMessages;
import br.com.alelo.consumer.consumerpat.domain.repositories.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.domain.services.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.alelo.consumer.consumerpat.domain.exceptions.ErrorMessages.CONSUMER_NOT_FOUND;

@Slf4j
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    ConsumerRepository consumerRepository;

    @Override
    public List<Consumer> findAll(int page, int size) throws ConsumersNotFoundException {
        List<Consumer> consumers = consumerRepository.findAll(page, size);
        if (consumers.isEmpty()) {
            log.warn(ErrorMessages.CONSUMERS_NOT_FOUND.message());

            throw new ConsumersNotFoundException(ErrorMessages.CONSUMERS_NOT_FOUND.message());
        }
        return consumers;
    }

    @Override
    public Consumer save(Consumer consumer) {
        return consumerRepository.save(consumer);
    }

    @Override
    public Consumer update(Consumer consumer) throws ConsumerNotFoundException {
        if (!consumerRepository.findById(consumer.getId())) {
            String message = String.format(CONSUMER_NOT_FOUND.message(), consumer.getId());
            log.error(message);

            throw new ConsumerNotFoundException(message);
        }
        consumer.removeCards();

        return consumerRepository.update(consumer);
    }


}
