package br.com.alelo.consumer.consumerpat.services.impl;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerNameNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.ExtractNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.services.ExtractService;
import org.springframework.stereotype.Service;

@Service
public class ExtractServiceImpl implements ExtractService {

    private final ExtractRepository extractRepository;

    public ExtractServiceImpl(ExtractRepository extractRepository) {
        this.extractRepository = extractRepository;
    }

    @Override
    public void save(Extract extract) {
        extractRepository.save(extract);
    }

    @Override
    public Extract findByConsumerName(String name) {
        return extractRepository.findExtractByConsumerNameContaining(name)
                .orElseThrow(() -> new ConsumerNameNotFoundException(name) {
                });
    }

    @Override
    public Extract findOrFail(Long id) {
        return extractRepository.findById(id)
                .orElseThrow(() -> new ExtractNotFoundException(id) {
                });
    }
}
