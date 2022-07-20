package br.com.alelo.consumer.consumerpat.usecase;

import br.com.alelo.consumer.consumerpat.controller.model.BalanceRequest;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SetBalanceUsecase {

    private final ConsumerRepository repository;

    public void execute(BalanceRequest input, Integer id) {
        Consumer consumer = null;
        consumer = repository.findByIdAndDrugstoreNumber(id, input.getCardNumber());

        if (consumer != null) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + input.getValue());
            repository.save(consumer);
        } else {
            consumer = repository.findByIdAndFoodCardNumber(id, input.getCardNumber());
            if (consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + input.getValue());
                repository.save(consumer);
            } else {
                // É cartão de combustivel
                consumer = repository.findByIdAndFuelCardNumber(id, input.getCardNumber());
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + input.getValue());
                repository.save(consumer);
            }
        }
    }
}
