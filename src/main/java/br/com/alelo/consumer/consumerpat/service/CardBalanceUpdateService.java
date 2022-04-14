package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.request.CardBalanceUpdateRequestDto;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Deve creditar(adicionar) um valor(value) em um no cartão.
 * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
 * para isso deve usar o número do cartão(cardNumber) fornecido.
 */
@Service
public class CardBalanceUpdateService {

    @Autowired
    ConsumerRepository consumerRepository;

    public void setBalance(CardBalanceUpdateRequestDto cardBalanceUpdateRequestDto) {

        boolean updated = false;
        Optional<Consumer> consumer = consumerRepository.findByDrugstoreNumber(cardBalanceUpdateRequestDto.getCardNumber());
        if (consumer.isPresent()) {
            consumerRepository.save(this.addBalanceForDrugStoreCard(consumer.get(), cardBalanceUpdateRequestDto.getValue()));
            updated = true;
        }

        consumer = consumerRepository.findByFoodCardNumber(cardBalanceUpdateRequestDto.getCardNumber());
        if (!updated && consumer.isPresent()) {
            consumerRepository.save(this.addBalanceForFoodCard(consumer.get(), cardBalanceUpdateRequestDto.getValue()));
            updated = true;
        }

        consumer = consumerRepository.findByFuelCardNumber(cardBalanceUpdateRequestDto.getCardNumber());
        if (!updated && consumer.isPresent()) {
            consumerRepository.save(this.addBalanceForFuelStoreCard(consumer.get(), cardBalanceUpdateRequestDto.getValue()));
        }
    }

    private Consumer addBalanceForDrugStoreCard(Consumer consumer, double valueToAdd) {
        double newValue = consumer.getDrugstoreCardBalance();
        newValue += valueToAdd;
        consumer.setDrugstoreCardBalance(newValue);
        return consumer;
    }

    private Consumer addBalanceForFoodCard(Consumer consumer, double valueToAdd) {
        double newValue = consumer.getFoodCardBalance();
        newValue += valueToAdd;
        consumer.setFoodCardBalance(newValue);
        return consumer;
    }

    private Consumer addBalanceForFuelStoreCard(Consumer consumer, double valueToAdd) {
        double newValue = consumer.getFuelCardBalance();
        newValue += valueToAdd;
        consumer.setFuelCardBalance(newValue);
        return consumer;
    }
}
