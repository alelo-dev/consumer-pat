package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@Service
public class ExtratcService {
    
    private final ExtractRepository repository;

    @Autowired
    public ExtratcService(ExtractRepository repository) {
        this.repository = repository;
    }
    
    public List<Extract> findAllByConsumer(String consumerName) {
        return this.repository.findByCardConsumerName(consumerName);
    }

    public Extract createExtract(Extract entity) {
        return this.repository.save(entity);
    }

}
