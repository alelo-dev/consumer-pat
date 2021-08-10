package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ConsumerService {

    List<ConsumerDTO> getConsumers();

    void saveConsumer (ConsumerDTO consumer);

    void updateConsumer (ConsumerDTO consumer);

    void setBalance(int cardNumber, double value);

    void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value);

}
