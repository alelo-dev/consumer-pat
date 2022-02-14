package br.com.alelo.consumer.consumerpat;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public class ConsumerProvider {

    public static Consumer createInitialConsumer(double initialBalance) {
        Consumer consumer = new Consumer();
        consumer.setId(1);
        consumer.setFoodCardBalance(initialBalance);
        consumer.setDrugstoreCardBalance(initialBalance);
        consumer.setFuelCardBalance(initialBalance);
        return consumer;

    }
}