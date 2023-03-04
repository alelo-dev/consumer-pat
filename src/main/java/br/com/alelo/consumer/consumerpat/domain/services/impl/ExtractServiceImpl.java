package br.com.alelo.consumer.consumerpat.domain.services.impl;

import br.com.alelo.consumer.consumerpat.domain.entities.Extract;
import br.com.alelo.consumer.consumerpat.domain.exceptions.ErrorMessages;
import br.com.alelo.consumer.consumerpat.domain.exceptions.ExtractNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.repositories.ExtractRepository;
import br.com.alelo.consumer.consumerpat.domain.services.ExtractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ExtractServiceImpl implements ExtractService {

    @Autowired
    ExtractRepository extractRepository;

    @Override
    public void save(Extract extract) {
        extractRepository.save(extract);
    }

    @Override
    public List<Extract> findAll(int consumerId, int page, int size) throws ExtractNotFoundException {
        List<Extract> extracts = extractRepository.findAll(consumerId, page, size);
        if (extracts.isEmpty()) {
            String message = String.format(ErrorMessages.EXTRACTS_NOT_FOUND.message(), consumerId);
            log.warn(message);

            throw new ExtractNotFoundException(message);
        }
        return extracts;
    }
}
