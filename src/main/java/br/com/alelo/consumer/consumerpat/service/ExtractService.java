package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ExtractService {
    @Autowired
    ExtractRepository extractRepository;

    public Extract save(@RequestBody Extract extract) {
        return extractRepository.save(extract);
    }
}
