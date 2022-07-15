package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviços relativos ao extrato das transações do cliente.
 */
@Service
public class ExtractService {

    private ExtractRepository extractRepository;

    @Autowired
    public ExtractService(ExtractRepository extractRepository) {
        this.extractRepository = extractRepository;
    }

    public ExtractRepository getExtractRepository() {
        return extractRepository;
    }

}
