package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enums.ConsumerEnum;
import br.com.alelo.consumer.consumerpat.model.BuyModel;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
public class ConsumerService {

    static ConsumerRepository consumerRepository;

    ExtractService extractService;

    public Page<Consumer> getAllConsumers(int page, int itemsPerPage) {
        return consumerRepository.getAllConsumers(PageRequest.of(page, itemsPerPage));
    }

    public Consumer save(Consumer consumer) {
        return consumerRepository.save(consumer);
    }

    public Consumer update(Consumer consumer) {

        Optional<Consumer> databaseEntity = consumerRepository.findById(Math.toIntExact(consumer.getId()));

        if (databaseEntity.isPresent()) {

            consumer.setDrugstoreCardBalance(databaseEntity.get().getDrugstoreCardBalance());
            consumer.setFuelCardBalance(databaseEntity.get().getFuelCardBalance());
            consumer.setFoodCardBalance(databaseEntity.get().getFoodCardBalance());

        } else
            throw new NotFoundException(consumer.getId().toString());

        return this.save(consumer);
    }

    public static Consumer addValue(Integer cardNumber, BigDecimal value) {

        Consumer consumer = findByAnyCard(cardNumber);

        if(cardNumber == consumer.getDrugstoreNumber()) {
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance().add(value));
        } else if(cardNumber == consumer.getFoodCardNumber()) {
            consumer.setFoodCardBalance(consumer.getFoodCardBalance().add(value));
        } else if(cardNumber == consumer.getFuelCardNumber()) {
            consumer.setFuelCardBalance(consumer.getFuelCardBalance().add(value));
        }

        return consumerRepository.save(consumer);
    }
    public Consumer buy(BuyModel buyModel) {

        Consumer consumer = this.findByAnyCard(buyModel.getCardNumber());

        ConsumerEnum.fromValue(buyModel.getType());

        extractService.save(
                new Extract(buyModel.getName(), buyModel.getProductDescription(), new Date(), buyModel.getCardNumber(), buyModel.getValue()));

        return consumerRepository.save(consumer);
    }

    private static Consumer findByAnyCard(Integer cardNumber) {
        Optional<Consumer> optionalConsumer = consumerRepository.findByCardNumber(cardNumber);

        if(optionalConsumer.isEmpty())
            throw new NotFoundException(cardNumber.toString());

        return optionalConsumer.get();
    }
}
