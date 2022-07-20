package br.com.alelo.consumer.consumerpat.usecase;

import br.com.alelo.consumer.consumerpat.controller.model.BuyRequest;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import java.util.Date;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BuyUsecase {

    private final ExtractRepository extractRepository;
    private final ConsumerRepository repository;

    public void execute(BuyRequest input) {
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */

        switch (input.getEstablishmentType()) {
            case ALIMENTACAO: processFood(input);
                break;
            case FARMACIA: processDrugStore(input);
                break;
            default: processDefault(input);
                break;
        }

        Extract extract = new Extract(input.getEstablishmentName(), input.getProductDescription(), new Date(), input.getCardNumber(), input.getValue());
        extractRepository.save(extract);
    }

    private void processDefault(final BuyRequest input) {
        Consumer consumer = null;
        // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
        double tax = (input.getValue() / 100) * 35;
        double value = input.getValue() + tax;

        consumer = repository.findByFuelCardNumber(input.getCardNumber());
        consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
        repository.save(consumer);
    }

    private void processDrugStore(final BuyRequest input) {
        Consumer consumer = null;
        consumer = repository.findByDrugstoreNumber(input.getCardNumber());
        consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - input.getValue());
        repository.save(consumer);
    }

    private void processFood(BuyRequest input) {
        Consumer consumer = null;
        // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
        double cashback = (input.getValue() / 100) * 10;
        double value = input.getValue() - cashback;

        consumer = repository.findByFoodCardNumber(input.getCardNumber());
        consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
        repository.save(consumer);
    }

}
