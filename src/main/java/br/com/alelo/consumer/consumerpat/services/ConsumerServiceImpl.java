package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    private final ConsumerRepository repository;
    private final ExtractRepository extractRepository;

    @Override
    public List<Consumer> listAllConsumers() {
        return repository.getAllConsumersList();
    }

    @Override
    @Transactional
    public void createConsumer(final Consumer consumer) {
        repository.save(consumer);
    }

    @Override
    @Transactional
    public void updateConsumer(final Consumer consumer) {
        repository.save(consumer);
    }

    @Override
    @Transactional
    public void setBalance(final int cardNumber, final double value) {
        //save can be done at the end
        Consumer consumer = null;
        consumer = repository.findByDrugstoreNumber(cardNumber);

        if (consumer != null) { //TODO change to nonNull function
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreNumber() + value);
            repository.save(consumer);
        } else {
            consumer = repository.findByFoodCardNumber(cardNumber);
            if (consumer != null) {
                consumer.setFoodCardBalance(consumer.getFoodCardNumber() + value);
                repository.save(consumer);
            } else {
                consumer = repository.findByFuelCardNumber(cardNumber);
                consumer.setFuelCardBalance(consumer.getFuelCardNumber() + value);
                repository.save(consumer);
            }
        }
    }

    @Override
    @Transactional
    public void buy(final int establishmentType, final String establishmentName, final int cardNumber,
                    final String productDescription, double value) {

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
            Double cashback  = (value / 100) * 10;
            value = value - cashback;

            consumer = repository.findByFoodCardNumber(cardNumber);
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
            repository.save(consumer);

        }else if(establishmentType == 2) {
            consumer = repository.findByDrugstoreNumber(cardNumber);
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
            repository.save(consumer);

        } else {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            Double tax  = (value / 100) * 35;
            value = value + tax;

            consumer = repository.findByFuelCardNumber(cardNumber);
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
            repository.save(consumer);
        }

        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
        extractRepository.save(extract);

    }
}
