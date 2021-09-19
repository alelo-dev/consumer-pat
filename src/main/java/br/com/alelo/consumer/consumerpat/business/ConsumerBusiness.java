package br.com.alelo.consumer.consumerpat.business;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.utils.exception.ProcessException;

import java.util.List;

public interface ConsumerBusiness {

    List<Consumer> listAllConsumers();

    void createConsumer(Consumer consumer) throws ProcessException;

    void updateConsumer(Consumer consumer) throws ProcessException;

  }
