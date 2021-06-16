package br.com.alelo.consumer.consumerpat.application.impl;

import br.com.alelo.consumer.consumerpat.application.ConsumerApplicationService;
import br.com.alelo.consumer.consumerpat.domain.model.Consumer;
import br.com.alelo.consumer.consumerpat.domain.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.domain.service.ExtractService;
import br.com.alelo.consumer.consumerpat.enums.EstablishmentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ConsumerApplicationServiceImpl implements ConsumerApplicationService {

    private final ConsumerService consumerService;
    private final ExtractService extractService;


    public List<Consumer> listAllConsumers(){
        return consumerService.listAllConsumers();
    }

    public void createConsumer(Consumer consumer) {
        consumerService.save(consumer);
    }

    public void updateConsumer(Consumer consumer) {
        consumerService.save(consumer);
    }


    public void setBalance(int cardNumber, double value) {
        consumerService.setBalance(cardNumber, value);
    }


    public void buy(EstablishmentType establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        value = consumerService.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
        extractService.saveExtract(establishmentName, productDescription, LocalDate.now(), cardNumber, value);
    }
}
