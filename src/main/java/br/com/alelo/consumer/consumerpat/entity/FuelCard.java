package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

public class FuelCard implements Card{

    private ConsumerRepository repository;

    public FuelCard(ConsumerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Consumer CreditValue(int cardNumber, double value) {
        Consumer consumer = null;
        consumer = repository.findByFuelCardNumber(cardNumber);

        if (consumer != null){
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
            repository.save(consumer);
        }
        return consumer;
    }
}
