package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static java.util.Objects.nonNull;

import static br.com.alelo.consumer.consumerpat.constants.EstablishmentTypes.FOOD_ESTABLISHMENT;
import static br.com.alelo.consumer.consumerpat.constants.EstablishmentTypes.DRUGSTORE_ESTABLISHMENT;
import static br.com.alelo.consumer.consumerpat.constants.EstablishmentTypes.FUEL_ESTABLISHMENT;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    private final ConsumerRepository repository;
    private final ExtractRepository extractRepository;

    @Override
    public List<Consumer> listAllConsumers() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Consumer createConsumer(final Consumer consumer) {

        log.info("ConsumerServiceImpl.createConsumer - Start");
        log.debug("ConsumerServiceImpl.createConsumer - Start - Input - Consumer: {}", consumer);

        return repository.save(consumer);
    }

    @Override
    @Transactional
    public Consumer updateConsumer(final Consumer consumer) {

        log.info("ConsumerServiceImpl.updateConsumer - Start");
        log.debug("ConsumerServiceImpl.updateConsumer - Start - Input - Consumer: {}", consumer);

        return repository.save(consumer);
    }

    @Override
    @Transactional
    public void setCardBalance(final int cardNumber, final double value) {

        log.info("ConsumerServiceImpl.setCardBalance - Start");
        log.debug("ConsumerServiceImpl.setCardBalance - Start - Input - Card Number: {}, Value: {}", cardNumber, value);

        Consumer consumer = repository.findByCardDrugstoreNumber(cardNumber); //TODO VERIFY THIS - does it return null or []

        if (nonNull(consumer)) {
            consumer.getCard()
                    .setDrugstoreCardBalance(consumer.getCard().getDrugstoreNumber() + value);
        } else {
            consumer = repository.findByCardFoodCardNumber(cardNumber);
            if (nonNull(consumer)) {
                consumer.getCard()
                        .setFoodCardBalance(consumer.getCard().getFoodCardNumber() + value);
            } else {
                consumer = repository.findByCardFuelCardNumber(cardNumber);
                consumer.getCard()
                        .setFuelCardBalance(consumer.getCard().getFuelCardNumber() + value);
            }

        }
        repository.save(consumer);
    }

    /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
     *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
     *
     * Tipos de estabelcimentos
     * 1 - Alimentação (food)
     * 2 - Farmácia (DrugStore)
     * 3 - Posto de combustivel (Fuel)
     */
    @Override
    @Transactional
    public void buy(final int establishmentType, final String establishmentName, final int cardNumber, //TODO verify if it can return type Extract
                    final String productDescription, double value) {

        Consumer consumer = null;

        //https://stackoverflow.com/questions/24944906/java-call-class-method-based-on-value-of-variable
        if (establishmentType == FOOD_ESTABLISHMENT) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            Double cashback  = (value / 100) * 10;
            value = value - cashback;

            consumer = repository.findByCardFoodCardNumber(cardNumber);
            consumer.getCard()
                    .setFoodCardBalance(consumer.getCard().getFoodCardBalance() - value);

            repository.save(consumer);

        } else if (establishmentType == DRUGSTORE_ESTABLISHMENT) {
            consumer = repository.findByCardDrugstoreNumber(cardNumber);
            consumer.getCard()
                    .setDrugstoreCardBalance(consumer.getCard().getDrugstoreCardBalance() - value);

            repository.save(consumer);

        } else if (establishmentType == FUEL_ESTABLISHMENT) {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            double tax  = (value / 100) * 35;
            value = value + tax;

            consumer = repository.findByCardFuelCardNumber(cardNumber);
            consumer.getCard()
                    .setFuelCardBalance(consumer.getCard().getFuelCardBalance() - value);
            repository.save(consumer);
        }

        //TODO create exceptions NotFound

        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
        extractRepository.save(extract);

    }
}
