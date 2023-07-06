package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.model.BuyModel;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Date;
import java.util.Optional;

@Service
public class ConsumerService {

    ConsumerRepository repository;

    ExtractService extractService;

    ConsumerService(ConsumerRepository repository, ExtractService extractService){
        this.repository = repository;
        this.extractService = extractService;
    }

    public Page<Consumer> getAllConsumers(int page, int itemsPerPage) {
        return repository.getAllConsumers(PageRequest.of(page, itemsPerPage));
    }

    public Consumer save(Consumer consumer) {
        return repository.save(consumer);
    }

    public Consumer update(Consumer consumer) {

        Optional<Consumer> databaseEntity = repository.findById(consumer.getId());

        if (databaseEntity.isPresent()) {

            consumer.setDrugstoreCardBalance(databaseEntity.get().getDrugstoreCardBalance());
            consumer.setFuelCardBalance(databaseEntity.get().getFuelCardBalance());
            consumer.setFoodCardBalance(databaseEntity.get().getFoodCardBalance());

        } else
            throw new NotFoundException(consumer.getId().toString());

        return this.save(consumer);
    }

    public Consumer addValue(Integer cardNumber, Double value) {

        Consumer consumer = findByAnyCard(cardNumber);

        if(cardNumber == consumer.getDrugstoreNumber()) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);

        } else if(cardNumber == consumer.getFoodCardNumber()) {
            // é cartão de refeição
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);

        } else if(cardNumber == consumer.getFuelCardNumber()) {
            // É cartão de combustivel
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
        }

        return repository.save(consumer);
    }
    public Consumer buy(BuyModel buyModel) {

        Consumer consumer = this.findByAnyCard(buyModel.getCardNumber());

        buyModel.getEstablishmentType()
                .updateBalance(buyModel.getValue(), buyModel.getCardNumber(), consumer);

        extractService.save(
                new Extract(buyModel.getEstablishmentName(), buyModel.getProductDescription(), new Date(), buyModel.getCardNumber(), buyModel.getValue()));

        return repository.save(consumer);
    }

    private Consumer findByAnyCard(Integer cardNumber) {
        Optional<Consumer> optionalConsumer = repository.findByAnyCardNumber(cardNumber);

        if(optionalConsumer.isEmpty())
            throw new NotFoundException(cardNumber.toString());

        return optionalConsumer.get();
    }
}
