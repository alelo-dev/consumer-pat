package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class ExtractService {

    private final ExtractRepository extractRepository;

    public void save(String establishmentName, String productDescription, int cardNumber, double amount) {
        extractRepository.save(new Extract(establishmentName, productDescription, new Date(), cardNumber, amount));
    }

}
