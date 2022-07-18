package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @Autowired
    ConsumerRepository repository;

    public String findCard (int ...cardNumber) {
        Consumer consumer;
        for (int card : cardNumber) {
            consumer = repository.findByDrugstoreNumber(card);
            if(consumer != null) {
                return "DRUGSTORE";
            }

            consumer = repository.findByFoodCardNumber(card);
            if(consumer != null) {
                return "FOOD";
            }

            consumer = repository.findByFuelCardNumber(card);
            if(consumer != null) {
                return "FUEL";
            }
        }
        return "";
    }

    public Boolean isDuplicate (int foodCard, int fuelCard, int drugStoreCard) {
        if (foodCard == fuelCard || foodCard == drugStoreCard || fuelCard == drugStoreCard)
            return true;

        return false;
    }

    public Consumer findById (int id) {
        Consumer consumer = repository.findById(id);
        return consumer;
    }

    public void saveConsumer (Consumer consumer) {
        repository.save(consumer);
    }

    public void setCardBalance(String cardType, int cardNumber, double value) {
        Consumer consumer;
        switch (cardType) {
            case "DRUGSTORE":
                consumer = repository.findByDrugstoreNumber(cardNumber);
                consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
                saveConsumer(consumer);
                break;
            case "FOOD":
                consumer = repository.findByFoodCardNumber(cardNumber);
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
                saveConsumer(consumer);
                break;
            case "FUEL":
                consumer = repository.findByFuelCardNumber(cardNumber);
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
                saveConsumer(consumer);
                break;
        }
    }
}
