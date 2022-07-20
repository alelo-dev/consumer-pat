package br.com.alelo.consumer.consumerpat.usecase.strategy;

import br.com.alelo.consumer.consumerpat.controller.model.BuyRequest;
import br.com.alelo.consumer.consumerpat.controller.model.EstablishmentType;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProcessCardDefaultStrategy implements ProcessCardStrategy {

    private final ConsumerRepository repository;

    @Override
    public boolean shouldExecute(final BuyRequest input) {
        return (EstablishmentType.FOOD != input.getEstablishmentType()
            && EstablishmentType.DRUG_STORE != input.getEstablishmentType());
    }

    @Override
    public boolean execute(final BuyRequest input) {
        try {
            Consumer consumer = null;
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            double tax = (input.getValue() / 100) * 35;
            double value = input.getValue() + tax;

            consumer = repository.findByFuelCardNumber(input.getCardNumber());
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
            repository.save(consumer);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
