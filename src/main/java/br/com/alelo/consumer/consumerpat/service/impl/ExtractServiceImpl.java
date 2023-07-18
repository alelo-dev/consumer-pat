package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import org.springframework.stereotype.Service;

@Service
public class ExtractServiceImpl implements ExtractService {
    ExtractRepository repository;

    public ExtractServiceImpl(ExtractRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Extract extract) {
        repository.save(extract);
    }
}
