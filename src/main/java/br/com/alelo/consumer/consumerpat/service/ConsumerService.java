package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;

import java.util.List;

public interface ConsumerService {
    void setCardBalance(Integer cardNumber, Double value) throws CardNotFoundException;
    void  buy(Integer establishmentType, String establishmentName, Integer cardNumber, String productDescription, Double value) throws CardNotFoundException;
    Consumer saveNewConsumer(Consumer consumer);
    Consumer updateConsumer(Consumer consumer);
    List<Consumer> findAll();

}
