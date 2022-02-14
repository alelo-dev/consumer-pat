package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerNotFoundException;

import java.util.List;

public interface ConsumerService {
    void setCardBalance(Integer cardNumber, Double value) throws CardNotFoundException;
    void  buy(Integer establishmentType, String establishmentName, Integer cardNumber, String productDescription, Double value) throws CardNotFoundException;
    Consumer saveNewConsumer(Consumer consumer);
    Consumer updateConsumer(ConsumerDto consumer, Integer consumerId) throws ConsumerNotFoundException;
    List<Consumer> findAll();

}
