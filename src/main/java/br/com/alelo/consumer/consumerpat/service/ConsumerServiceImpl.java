package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ConsumerServiceImpl implements IConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private ExtractRepository extractRepository;

    public static final int FOOD = 1, DRUGSTORE = 2, FUEL = 3;

    @Override
    public List<Consumer> listAllConsumers() {
        return consumerRepository.findAll();
    }

    @Override
    public void createConsumer(Consumer consumer) {
        consumerRepository.save(consumer);
    }

    @Override
    public void updateConsumer(ConsumerDTO consumer) {

        Optional<Consumer> updatedConsumer = consumerRepository.findById(consumer.getId());
        if (updatedConsumer.isPresent()) {
            BeanUtils.copyProperties(consumer, updatedConsumer.get());
            consumerRepository.save(updatedConsumer.get());
        }
    }

    @Override
    public void setBalance(String cardNumber, BigDecimal value) {

        Optional<Consumer> optionalConsumer = consumerRepository.findByCardNumber(cardNumber);

        if (optionalConsumer.isPresent()) {
            Consumer consumer = optionalConsumer.get();
            // é cartão de farmácia
            if (consumer.getDrugStoreNumber().equals(cardNumber)) {
                consumer.setDrugStoreCardBalance(consumer.getDrugStoreCardBalance().add(value));
            }

            // é cartão de refeição
            if (consumer.getFoodCardNumber().equals(cardNumber)) {
                consumer.setFoodCardBalance(consumer.getFoodCardBalance().add(value));
            }
            // É cartão de combustivel
            if (consumer.getFuelCardNumber().equals(cardNumber)) {
                consumer.setFuelCardBalance(consumer.getFuelCardBalance().add(value));
            }

            consumerRepository.save(consumer);
        }
    }

    @Override
    public void buy(int establishmentType, String establishmentName, String cardNumber, String productDescription, BigDecimal value) {

        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */

        Optional<Consumer> consumerOptional = null;

            if (establishmentType == FOOD) {

                consumerOptional = setFoodCardBalance(cardNumber,value);

            } else if (establishmentType == DRUGSTORE) {
                consumerOptional = setDrugStoreCardBalance(cardNumber,value);


            } else if (establishmentType == FUEL) {
                consumerOptional = setFuelCardBalance(cardNumber,value);
            }

            if(consumerOptional.isPresent()) {
                consumerRepository.save(consumerOptional.get());

                Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
                extractRepository.save(extract);
            }
        }

    private Optional<Consumer> setFuelCardBalance(String cardNumber, BigDecimal value) {
        // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
        Optional<Consumer> consumerOptional = consumerRepository.findByFuelCardNumber(cardNumber);
        if(consumerOptional.isPresent()) {
            Consumer consumer = consumerOptional.get();
            BigDecimal tax = calculatePercentage(value, 35);
            value = value.add(tax);
            consumer.setFuelCardBalance(consumer.getFuelCardBalance().subtract(value));
        }
        return consumerOptional;
    }

    private Optional<Consumer> setDrugStoreCardBalance(String cardNumber, BigDecimal value) {
        Optional<Consumer> consumerOptional = consumerRepository.findByDrugStoreNumber(cardNumber);
        if(consumerOptional.isPresent()) {
            Consumer consumer = consumerOptional.get();
            consumer.setDrugStoreCardBalance(consumer.getDrugStoreCardBalance().subtract(value));
        }

        return consumerOptional;
    }

    private Optional<Consumer> setFoodCardBalance(String cardNumber,BigDecimal value) {
        // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
        Optional<Consumer> consumerOptional = consumerRepository.findByFoodCardNumber(cardNumber);
        if(consumerOptional.isPresent()) {
            Consumer consumer = consumerOptional.get();
            BigDecimal cashback = calculatePercentage(value, 10);
            value = value.subtract(cashback);
            consumer.setFoodCardBalance(consumer.getFoodCardBalance().subtract(value));
        }

        return consumerOptional;
    }


    private BigDecimal calculatePercentage(BigDecimal value, int perc) {
        return (value.divide(BigDecimal.valueOf(100))).multiply(BigDecimal.valueOf(perc));
    }
}
