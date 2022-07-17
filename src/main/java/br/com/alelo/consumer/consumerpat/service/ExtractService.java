package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ExtractService {

    @Autowired
    private ExtractRepository extractRepository;

    @Autowired
    private EstablishmentService establishmentService;

    public void registerSale(int establishmentType, String establishmentName, String productDescription, Date date, int cardNumber, double value) {

        Establishment establishment = establishmentService.findEstablishmentByNameAndType(establishmentName, establishmentType);
        Extract extract = new Extract(establishment, productDescription, date, cardNumber, value);
        extractRepository.save(extract);

    }
}
