package br.com.alelo.consumer.consumerpat.business.service;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 09:45
 */

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.ExtractCreationException;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExtractBService {

    private final ExtractRepository extractRepository;

    public ExtractBService(ExtractRepository extractRepository) {
        this.extractRepository = extractRepository;
    }

    public Extract save(Extract extract){
        try{
            return this.extractRepository.save(extract);
        }catch (Exception e){
            log.error("Failed to extract on card -> {}", extract.getCardNumber());
            log.error("Full log error", e);
            throw new ExtractCreationException();
        }
    }
}
