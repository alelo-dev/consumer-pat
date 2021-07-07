package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;


public class FoodCard implements Card{

    private ConsumerRepository repository;

    public FoodCard(ConsumerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Consumer CreditValue(int cardNumber, double value) {
        Consumer consumer = null;
        consumer = repository.findByFoodCardNumber(cardNumber);

        if (consumer != null){
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
            repository.save(consumer);
        }
        return consumer;
    }
}
