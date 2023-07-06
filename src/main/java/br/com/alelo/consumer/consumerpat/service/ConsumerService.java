package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerService {

    ConsumerRepository repository;

    ExtractRepository extractRepository;

    ConsumerService(ConsumerRepository repository, ExtractRepository extractRepository){
        this.repository = repository;
        this.extractRepository = extractRepository;
    }

    public Page<Consumer> getAllConsumersList(Integer page, Integer itemsPerPage) {
        return repository.getAllConsumersList(PageRequest.of(page, itemsPerPage));
    }
}
