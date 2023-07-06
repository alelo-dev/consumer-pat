package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.stereotype.Service;

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
