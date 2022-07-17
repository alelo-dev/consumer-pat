package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.service.strategy.establishment.EstablishmentStrategy;
import br.com.alelo.consumer.consumerpat.service.strategy.establishment.EstablishmentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ConsumerService {

    @Autowired
    private CardService cardService;

    @Autowired
    private ExtractService extractService;

    @Autowired
    private EstablishmentStrategy establishmentStrategy;

    @Autowired
    private EstablishmentFactory factory;

    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {

        EstablishmentStrategy establishmentStrategy = factory.getEstablishent(establishmentType);
        double withdrawal = establishmentStrategy.calcValueToDeb(value);
        cardService.setWithdrawal(cardNumber, withdrawal);
        extractService.registerSale(establishmentType, establishmentName, productDescription, new Date(), cardNumber, value);

    }

}
