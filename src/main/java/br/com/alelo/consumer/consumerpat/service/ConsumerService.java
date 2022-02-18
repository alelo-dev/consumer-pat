package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.CannotCreateExistingConsumerException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.InvalidEstablishmentTypeException;
import br.com.alelo.consumer.consumerpat.exception.NotEnoughBalanceException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final ExtractService extractService;

    private final ConsumerRepository consumerRepository;


    public List<Consumer> getAllConsumersList() {
        return consumerRepository.getAllConsumersList();
    }

    public void createConsumer(Consumer consumer) {
        if (!Objects.isNull(consumer.getId())) {
            throw new CannotCreateExistingConsumerException();
        }
        consumerRepository.save(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    public void updateConsumer(Consumer consumer) {
        Consumer existingConsumer = consumerRepository.findById(consumer.getId())
                .orElseThrow(ConsumerNotFoundException::new);

        // Lançar exceção se os saldos forem alterados?
        consumer.setFoodCardBalance(existingConsumer.getFoodCardBalance());
        consumer.setDrugstoreCardBalance(existingConsumer.getDrugstoreCardBalance());
        consumer.setFuelCardBalance(existingConsumer.getFuelCardBalance());

        consumerRepository.save(consumer);
    }

    public void setBalance(int cardNumber, double value) {
        Consumer consumer = consumerRepository.findByDrugstoreNumber(cardNumber);

        if (consumer != null) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
            consumerRepository.save(consumer);
        } else {
            consumer = consumerRepository.findByFoodCardNumber(cardNumber);
            if (consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
                consumerRepository.save(consumer);
            } else {
                // É cartão de combustivel
                consumer = consumerRepository.findByFuelCardNumber(cardNumber);
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
                consumerRepository.save(consumer);
            }
        }
    }

    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        Consumer consumer = null;
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */

        switch (establishmentType) {
            case 1:
                Double cashback = (value / 100) * 10;
                value = value - cashback;

                consumer = consumerRepository.findByFoodCardNumber(cardNumber);

                if (!hasEnoughBalance(consumer.getFoodCardBalance(), value)) {
                    throw new NotEnoughBalanceException();
                }

                consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
                consumerRepository.save(consumer);
                break;
            case 2:
                consumer = consumerRepository.findByDrugstoreNumber(cardNumber);

                if (!hasEnoughBalance(consumer.getDrugstoreCardBalance(), value)) {
                    throw new NotEnoughBalanceException();
                }

                consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
                consumerRepository.save(consumer);
                break;
            case 3:
                // Nas compras com o cartão de combustível existe um acréscimo de 35%;
                double tax = (value / 100) * 35;
                value = value + tax;

                consumer = consumerRepository.findByFuelCardNumber(cardNumber);

                if (!hasEnoughBalance(consumer.getFuelCardBalance(), value)) {
                    throw new NotEnoughBalanceException();
                }

                consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
                consumerRepository.save(consumer);
                break;
            default:
                throw new InvalidEstablishmentTypeException();
        }

        extractService.save(establishmentName, productDescription, cardNumber, value);
    }

    private boolean hasEnoughBalance(double balance, double value) {
        return balance >= value;
    }
}


