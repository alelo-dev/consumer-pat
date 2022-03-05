package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.domain.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtractServiceImpl implements ExtractService {
    @Autowired
    ExtractRepository extractRepository;

    @Override
    public Extract saveExtract(Extract extract) {
        return extractRepository.save(extract);
    }
}
