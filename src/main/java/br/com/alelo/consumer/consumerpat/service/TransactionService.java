package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class TransactionService {

    @Autowired
    private ConsumerRepository repository;

    @Autowired
    private ExtractRepository extractRepository;

    public void increaseBalance(Long cardNumber, BigDecimal value) {
        Consumer consumer = repository.findByDrugstoreNumber(cardNumber);
        System.out.println("DRUGSTORE CARD: " + consumer);
        if (consumer != null) {
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance().add(value));
            repository.save(consumer);
        }

        consumer = repository.findByFoodCardNumber(cardNumber);
        System.out.println("FOOD CARD: " + consumer);
        if (consumer != null) {
            consumer.setFoodCardBalance(consumer.getFoodCardBalance().add(value));
            repository.save(consumer);
        }

        consumer = repository.findByFuelCardNumber(cardNumber);
        System.out.println("FUEL CARD: " + consumer);
        if (consumer != null) {
            consumer.setFuelCardBalance(consumer.getFuelCardBalance().add(value));
            repository.save(consumer);
        }

    }

    public void buy(Integer establishmentType, String establishmentName, Long cardNumber, String productDescription, BigDecimal value) {
        Consumer consumer = null;
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */

        if (establishmentType == 1) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            BigDecimal cashback  = value.multiply(BigDecimal.valueOf(0.1));
            value = value.subtract(cashback);

            consumer = repository.findByFoodCardNumber(cardNumber);
            consumer.setFoodCardBalance(consumer.getFoodCardBalance().subtract(value));
            repository.save(consumer);

        } else if(establishmentType == 2) {
            consumer = repository.findByDrugstoreNumber(cardNumber);
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance().subtract(value));
            repository.save(consumer);

        } else {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            BigDecimal tax  = value.multiply(BigDecimal.valueOf(0.35));
            value = value.add(tax);

            consumer = repository.findByFuelCardNumber(cardNumber);
            consumer.setFuelCardBalance(consumer.getFuelCardBalance().subtract(value));
            repository.save(consumer);
        }

        Extract extract = new Extract();
        extract.setEstablishmentName(establishmentName);
        extract.setProductDescription(productDescription);
        extract.setDateBuy(new Date());
        extract.setCardNumber(cardNumber);
        extract.setValue(value);
        extractRepository.save(extract);
    }
}
