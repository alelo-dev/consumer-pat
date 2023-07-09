package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Log4j2
@Service
@Transactional
@AllArgsConstructor
public class ConsumerService {

    private final ConsumerRepository repository;
    private final ExtractService extractService;

    public Page<Consumer> getConsumerPageable(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Consumer> getById(Long id) {
        return repository.findById(id);
    }

    public void createConsumer(Consumer consumer) {
        repository.save(consumer);
    }

    public void updateConsumer(ConsumerDTO consumerDTO) {
        repository.save(toEntity(consumerDTO));
    }

    public void setBalance(Integer cardNumber, double value) {
        Consumer consumer;
        consumer = repository.findByDrugstoreNumber(cardNumber);

        if (consumer != null) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
            repository.save(consumer);
        } else {
            consumer = repository.findByFoodCardNumber(cardNumber);
            if (consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
                repository.save(consumer);
            } else {
                // É cartão de combustivel
                consumer = repository.findByFuelCardNumber(cardNumber);
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
                repository.save(consumer);
            }
        }
    }

    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        Consumer consumer;
        /* O valor só podem ser debitado do catão com o tipo correspondente ao tipo do estabelecimento da compra.

         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação (food) então o valor só pode ser debitado do cartão alimentação
         *
         * Tipos dos estabelcimentos:
         *    1) Alimentação (Food)
         *    2) Farmácia (DrugStore)
         *    3) Posto de combustivel (Fuel)
         */

        if (establishmentType == 1) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            double cashback = (value / 100) * 10;
            value = value - cashback;

            consumer = repository.findByFoodCardNumber(cardNumber);
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
            repository.save(consumer);

        } else if (establishmentType == 2) {
            consumer = repository.findByDrugstoreNumber(cardNumber);
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
            repository.save(consumer);

        } else {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            double tax = (value / 100) * 35;
            value = value + tax;

            consumer = repository.findByFuelCardNumber(cardNumber);
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
            repository.save(consumer);
        }

        extractService.save(establishmentName, productDescription, cardNumber, value);
    }

    public Consumer toEntity(ConsumerDTO consumerDTO) {
        Consumer consumer = new Consumer();

        return consumer;
    }
}
