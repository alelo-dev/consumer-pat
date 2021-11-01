package br.com.alelo.consumer.consumerpat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ExtractService;

@Service
public class ExtractServiceImpl implements ExtractService {

    @Autowired
    private ExtractRepository repository;

    @Override
    public List<Extract> list() {
        return repository.findAll();
    }

    @Override
    public void save(Extract extract) {
        repository.save(extract);
    }

}
