package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

public class DrugstoreCard implements Card{

    private ConsumerRepository repository;

    public DrugstoreCard(ConsumerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Consumer CreditValue(int cardNumber, double value) {
        Consumer consumer = null;
        consumer = repository.findByDrugstoreNumber(cardNumber);


        if (consumer != null){
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
            repository.save(consumer);

        }
        return consumer;
    }
}
