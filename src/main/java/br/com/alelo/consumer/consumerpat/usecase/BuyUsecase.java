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
        Consumer consumer = null;
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */

        if (input.getEstablishmentType() == 1) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            Double cashback = (input.getValue() / 100) * 10;
            double value = input.getValue() - cashback;

            consumer = repository.findByFoodCardNumber(input.getCardNumber());
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
            repository.save(consumer);

        } else if (input.getEstablishmentType() == 2) {
            consumer = repository.findByDrugstoreNumber(input.getCardNumber());
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - input.getValue());
            repository.save(consumer);

        } else {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            Double tax = (input.getValue() / 100) * 35;
            double value = input.getValue() + tax;

            consumer = repository.findByFuelCardNumber(input.getCardNumber());
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
            repository.save(consumer);
        }

        Extract extract = new Extract(input.getEstablishmentName(), input.getProductDescription(), new Date(), input.getCardNumber(), input.getValue());
        extractRepository.save(extract);
    }
}
