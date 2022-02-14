package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.exception.ConsumerException;

public interface ConsumerService {
    public List<Consumer> getAllConsumersList() throws ConsumerException;

    public void createConsumer(Consumer consumer) throws ConsumerException;

    public void updateConsumer(Consumer consumer) throws ConsumerException;

    public void addBalance(int cardNumber, double value) throws ConsumerException;

    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription,
            double value) throws ConsumerException;
}