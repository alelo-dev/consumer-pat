package br.com.alelo.consumer.consumerpat.usecase;

import br.com.alelo.consumer.consumerpat.controller.model.BuyRequest;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.usecase.strategy.ProcessCardStrategy;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BuyUsecase {

    private final List<ProcessCardStrategy> strategies;
    private final ExtractRepository extractRepository;

    public void execute(BuyRequest input) throws Exception {
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */

        Optional<ProcessCardStrategy> strategyOpt = strategies.stream().filter(s -> s.shouldExecute(input)).findFirst();

        if (strategyOpt.isPresent()) {
            extractRepository.save(input.toExtract());
        } else {
            throw new Exception("Error when execute buy");
        }
    }

}
