package br.com.alelo.consumer.consumerpat.service.impls;

import br.com.alelo.consumer.consumerpat.dto.BalanceDTO;
import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.interfaces.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.interfaces.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    ConsumerRepository consumerRepository;

    @Autowired
    ExtractService extractService;

    @Override
    public void insert(ConsumerDTO consumer) {
        return consumerRepository.save();
    }

    @Override
    public Consumer update(ConsumerDTO consumer) {
        return consumerRepository.save();
    }

    @Override
    public List<ConsumerDTO> getAllConsumersList() {
        return consumerRepository.getAllConsumersList();
    }

    @Override
    public void setBalance(BalanceDTO balance) {
        Consumer consumer = null;
        consumer = consumerRepository.findByDrugstoreNumber(balance.getCardNumber());

        if (consumer != null) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + balance.getValue());
            consumerRepository.save(consumer);
        } else {
            consumer = consumerRepository.findByFoodCardNumber(balance.getCardNumber());
            if (consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + balance.getValue());
                consumerRepository.save(consumer);
            } else {
                // É cartão de combustivel
                consumer = consumerRepository.findByFuelCardNumber(balance.getCardNumber());
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + balance.getValue());
                consumerRepository.save(consumer);
            }
        }
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

        if (buy.getEstablishmentType() == 1) {
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
