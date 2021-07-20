package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.validation.ConsumerValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@AllArgsConstructor
@SuppressWarnings("squid:S2583")
public class ConsumerServiceImpl implements ConsumerService {

    private ConsumerRepository repositoryConsumer;

    private ConsumerValidator validator;

    @Override
    public Consumer save(final Consumer consumer) {
        try{
            return repositoryConsumer.save(consumer);
        } catch (Exception ex) {
            throw new ResponseStatusException(BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @Override
    public Consumer update(final Consumer consumer) {

        validator.validateConsumer(consumer);
        validator.getConsumerUsingOptional(repositoryConsumer.findById(consumer.getId()));

        try {
            return repositoryConsumer.save(consumer);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        }
    }

    @Override
    public List<Consumer> listAllConsumers(final Pageable pageable) {
        try {
            if(repositoryConsumer.findAll(pageable) == null) {
                return Collections.emptyList();
            } else {
                return repositoryConsumer.findAll(pageable).getContent();
            }
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        }
    }
}
