package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Guilherme de Castro Fernandes
 * @created 01/09/2021 - 16:18
 */
@Service
public class ConsumerService {

    private final ConsumerRepository consumerRepository;

    @Autowired
    public ConsumerService(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    public List<Consumer> findAll() {
        return consumerRepository.findAll();
    }

    public void save(Consumer consumer) {
        consumerRepository.save(consumer);
    }

    public void update(Consumer consumer) {
    }

    public Consumer findByDrugstoreNumber(Integer cardNumber) {
        return consumerRepository.findByDrugstoreNumber(cardNumber);
    }

    public Consumer findByFuelCardNumber(int cardNumber) {
        return consumerRepository.findByFuelCardNumber(cardNumber);
    }

    public Consumer findByFoodCardNumber(int cardNumber) {
        return consumerRepository.findByFoodCardNumber(cardNumber);
    }
}
