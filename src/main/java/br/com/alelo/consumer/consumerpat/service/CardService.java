package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.ConsumerOrCardException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardService {
    @Autowired
    ConsumerRepository consumerRepository;

    public Consumer setBalance(int cardNumber, double value) throws ConsumerOrCardException {

        Consumer consumer = consumerRepository.findByDrugstoreNumber(cardNumber);

        if (consumer != null) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
        } else {
            consumer = consumerRepository.findByFoodCardNumber(cardNumber);
            if (consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
            } else {
                consumer = consumerRepository.findByFuelCardNumber(cardNumber);
                if (consumer != null) {
                    // É cartão de combustivel
                    consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
                } else {
                    throw new ConsumerOrCardException("Numero do Cartão não encontrado");
                }
            }
        }
        return consumerRepository.save(consumer);
    }
}
