package br.com.alelo.consumer.consumerpat.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.helper.RequestConsumerBuy;
import br.com.alelo.consumer.consumerpat.helper.RequestConsumerSetCardBalance;
import br.com.alelo.consumer.consumerpat.helper.RequestConsumerUpdate;

public interface ConsumerService {

    Page<Consumer> findConsumers(PageRequest page);
    void buy(RequestConsumerBuy requestConsumerBuy);
    void setBalance(RequestConsumerSetCardBalance requestConsumerSetCardBalance);
    void createConsumer(Consumer consumer);
    void updateConsumer(Long id, RequestConsumerUpdate requestConsumerUpdate);
}