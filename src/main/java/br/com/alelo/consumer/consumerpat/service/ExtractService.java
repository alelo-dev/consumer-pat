package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Guilherme de Castro Fernandes
 * @created 01/09/2021 - 16:19
 */

@Service
public class ExtractService {

    private final ExtractRepository extractRepository;

    @Autowired
    public ExtractService(ExtractRepository extractRepository) {
        this.extractRepository = extractRepository;
    }

    public void save(Extract extract) {
        extractRepository.save(extract);
    }
}
