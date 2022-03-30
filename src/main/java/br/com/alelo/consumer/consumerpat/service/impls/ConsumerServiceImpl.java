package br.com.alelo.consumer.consumerpat.service.impls;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.enums.TypeEnum;
import br.com.alelo.consumer.consumerpat.factory.EstablishmentFactory;
import br.com.alelo.consumer.consumerpat.parser.interfaces.ConsumerParser;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.interfaces.CardService;
import br.com.alelo.consumer.consumerpat.service.interfaces.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.interfaces.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private ExtractService extractService;

    @Autowired
    private CardService cardService;

    @Autowired
    private ConsumerParser consumerParser;

    @Override
    public Consumer insert(ConsumerDTO consumer) {
        return consumerRepository.save(consumerParser.parse(consumer));
    }

    @Override
    public Consumer update(ConsumerDTO consumer) {
        return consumerRepository.save(consumerParser.parse(consumer));
    }

    @Override
    public List<ConsumerDTO> getAllConsumersList() {
        return consumerParser.parse(consumerRepository.findAll());
    }

    @Override
    public void buy(BuyDTO buy) {
        Consumer consumer = null;
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */

        EstablishmentFactory.getInstance(TypeEnum. buy.getEstablishment().getType().getId())

        if (buy.getEstablishmentDTO().getType().getId() == 1) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            Double cashback = (buy.getValue() / 100) * 10;
            Double value = buy.getValue() - cashback;

            consumer = consumerRepository.findByFoodCardNumber(buy.getCardNumber());
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
            consumerRepository.save(consumer);
        } else if (buy.getEstablishmentType() == 2) {
            consumer = consumerRepository.findByDrugstoreNumber(buy.getCardNumber());
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - buy.getValue());
            consumerRepository.save(consumer);
        } else {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            Double tax = (buy.getValue() / 100) * 35;
            Double value = buy.getValue() + tax;

            consumer = consumerRepository.findByFuelCardNumber(buy.getCardNumber());
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
            consumerRepository.save(consumer);
        }

        extractService.insert(buy);
    }
}
