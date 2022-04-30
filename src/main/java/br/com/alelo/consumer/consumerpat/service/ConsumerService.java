package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

import java.math.BigDecimal;
import java.util.List;

public interface ConsumerService {

    List<Consumer> listAllConsumers();
    void update(Consumer consumer);
    void save(Consumer consumer);
    void setBalance(Long cardNumber, BigDecimal value);
    void buy(int establishmentType, String establishmentName, Long cardNumber, String productDescription, BigDecimal value);

}
