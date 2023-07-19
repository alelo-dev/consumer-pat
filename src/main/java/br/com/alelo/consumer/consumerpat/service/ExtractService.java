package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;

@Service
public class ExtractService {

    ExtractRepository extractRepository;

    public ExtractService(ExtractRepository extractRepository){
        this.extractRepository = extractRepository;
    }

    public Extract save(Extract extract) {
        return this.extractRepository.save(extract);
    }
}