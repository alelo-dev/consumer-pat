package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.BalanceUpdate;
import br.com.alelo.consumer.consumerpat.model.BuyCreate;
import br.com.alelo.consumer.consumerpat.model.ConsumerCreate;
import br.com.alelo.consumer.consumerpat.model.ConsumerUpdate;

import java.util.List;

public interface ConsumerService {
    void create(ConsumerCreate customerCreate);

    List<Consumer> getAll();

    void update(ConsumerUpdate consumerUpdate);

    void setBalance(BalanceUpdate balanceUpdate);

    void buy(BuyCreate buyCreate);
}
