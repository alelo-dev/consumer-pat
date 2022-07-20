package br.com.alelo.consumer.consumerpat.usecase.strategy;

import br.com.alelo.consumer.consumerpat.controller.model.BuyRequest;
import br.com.alelo.consumer.consumerpat.controller.model.EstablishmentType;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProcessFoodStrategy implements ProcessCardStrategy {

    private final ConsumerRepository repository;

    @Override
    public boolean shouldExecute(BuyRequest input) {
        return EstablishmentType.FOOD == input.getEstablishmentType();
    }

    @Override
    public boolean execute(BuyRequest input, Integer id) {
        try {
            Consumer consumer = null;
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            double cashback = (input.getValue() / 100) * 10;
            double value = input.getValue() - cashback;

            consumer = repository.findByIdAndFoodCardNumber(id, input.getCardNumber());
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
            repository.save(consumer);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
