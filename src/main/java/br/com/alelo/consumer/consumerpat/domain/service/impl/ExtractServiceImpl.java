package br.com.alelo.consumer.consumerpat.domain.service.impl;

import br.com.alelo.consumer.consumerpat.domain.entity.Extract;
import br.com.alelo.consumer.consumerpat.domain.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.domain.service.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Modela os serviços de gerenciamento de extrato das transações entre o consumidor
 * e os estabelecimentos conveniados.
 */
@Service
public class ExtractServiceImpl implements ExtractService {

    private final ExtractRepository extractRepository;

    @Autowired
    public ExtractServiceImpl(ExtractRepository extractRepository) {
        this.extractRepository = extractRepository;
    }

    @Override
    public Extract saveExtract(Extract extract) {
        return this.extractRepository.save(extract);
    }

}
