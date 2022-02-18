package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.consumer.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository repository;

    @Autowired
    private ExtractRepository extractRepository;

    public Page<Consumer> listAllConsumers(Pageable page) {
        return repository.findAll(Optional.ofNullable(page).orElse(Pageable.unpaged()));
    }

    public Consumer createConsumer(Consumer consumer) {
        return null;
    }

    public Consumer updateConsumer(Consumer consumer, Long id) {
        return null;
    }


}
