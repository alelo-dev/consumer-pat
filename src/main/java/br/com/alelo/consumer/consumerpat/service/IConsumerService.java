package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;

import java.math.BigDecimal;
import java.util.List;

public interface IConsumerService {

    List<Consumer> listAllConsumers();

    void createConsumer(Consumer consumer);

    void updateConsumer(ConsumerDTO consumer);

    void setBalance(String cardNumber, BigDecimal value);

    void buy(int establishmentType, String establishmentName, String cardNumber, String productDescription, BigDecimal value);
}
