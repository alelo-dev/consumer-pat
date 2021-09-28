package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.domain.dto.v2.BuyDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.ExtractEntity;
import br.com.alelo.consumer.consumerpat.domain.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtractServiceImpl implements ExtractService {

    @Autowired
    private ExtractRepository extractRepository;

    @Override
    public ExtractEntity save(BuyDTO buy) {
        return extractRepository.save(new ExtractEntity(buy));
    }
}
