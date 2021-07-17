package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.repository.IConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.validator.ConsumerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class ConsumerService implements IConsumerService {

    private final IConsumerRepository repository;

    private final ConsumerValidator validator;

    @Override
    public Consumer save(final Consumer consumer) {
        try{
            return repository.save(consumer);
        } catch (Exception ex) {
            throw new ResponseStatusException(BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @Override
    public Consumer update(final Consumer consumer) {

        validator.validConsumer(consumer);
        var consumerPersisted = validator.getConsumerByOptional(repository.findById(consumer.getId()));
        validator.validateBalanceForUpdate(consumerPersisted, consumer);

        try {
            return repository.save(consumer);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        }
    }

    @Override
    public Page<Consumer> findAll(final Pageable pageable) {
        try {
            return repository.findAll(pageable);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        }
    }
}
