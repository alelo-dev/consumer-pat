package br.com.alelo.consumer.consumerpat.usecase.strategy;

import br.com.alelo.consumer.consumerpat.controller.model.BuyRequest;
import br.com.alelo.consumer.consumerpat.controller.model.EstablishmentType;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProcessDrugStoreStrategy implements ProcessCardStrategy {

    private final ConsumerRepository repository;

    @Override
    public boolean shouldExecute(final BuyRequest input) {
        return EstablishmentType.DRUG_STORE == input.getEstablishmentType();
    }

    @Override
    public boolean execute(final BuyRequest input) {
        try {
            Consumer consumer = null;
            consumer = repository.findByDrugstoreNumber(input.getCardNumber());
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - input.getValue());
            repository.save(consumer);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
