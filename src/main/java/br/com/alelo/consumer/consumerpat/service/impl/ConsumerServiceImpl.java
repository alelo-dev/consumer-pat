package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.model.*;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    ConsumerRepository repository;
    ExtractService extractService;

    public ConsumerServiceImpl(ConsumerRepository repository, ExtractService extractService) {
        this.repository = repository;
        this.extractService = extractService;
    }

    @Override
    public void create(ConsumerCreate customerCreate) {
        repository.save(customerCreate.toEntity());
    }

    @Override
    public List<Consumer> getAll() {
        return repository.findAll();
    }

    @Override
    public void update(ConsumerUpdate consumerUpdate) {
        var consumerFound = repository.findById(consumerUpdate.getConsumerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consumer not found"));
        var customerToUpdate = consumerUpdate.toEntity();

        customerToUpdate.setConsumerId(consumerFound.getConsumerId());
        customerToUpdate.setFoodCardBalance(consumerFound.getFoodCardBalance());
        customerToUpdate.setFuelCardBalance(consumerFound.getFuelCardBalance());
        customerToUpdate.setDrugstoreCardBalance(consumerFound.getDrugstoreCardBalance());

        repository.save(customerToUpdate);
    }

    @Override
    public void setBalance(BalanceUpdate balanceUpdate) {
        var optionalConsumer = repository.findByDrugstoreCardNumber(balanceUpdate.getCardNumber());

        if (optionalConsumer.isPresent()) {
            var consumer = optionalConsumer.get();
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + balanceUpdate.getValue());
            repository.save(consumer);
            return;
        }

        optionalConsumer = repository.findByFuelCardNumber(balanceUpdate.getCardNumber());

        if (optionalConsumer.isPresent()) {
            var consumer = optionalConsumer.get();
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() + balanceUpdate.getValue());
            repository.save(consumer);
            return;
        }

        optionalConsumer = repository.findByFoodCardNumber(balanceUpdate.getCardNumber());

        if (optionalConsumer.isPresent()) {
            var consumer = optionalConsumer.get();
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() + balanceUpdate.getValue());
            repository.save(consumer);
            return;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card number not found");
    }

    @Override
    public void buy(BuyCreate buyCreate) {
        switch (EstablishmentType.getById(buyCreate.getEstablishmentType())) {
            case FOOD:
                var consumer = repository.findByFoodCardNumber(buyCreate.getCardNumber())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card number not found"));
                if (buyCreate.getValue() > consumer.getFoodCardBalance()) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Food balance lower than necessary");
                }
                double cashback = (buyCreate.getValue() / 100) * 10;
                buyCreate.setValue(buyCreate.getValue() - cashback);
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() - buyCreate.getValue());
                repository.save(consumer);
                break;
            case DRUGSTORE:
                consumer = repository.findByDrugstoreCardNumber(buyCreate.getCardNumber())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card number not found"));
                if (buyCreate.getValue() > consumer.getDrugstoreCardBalance()) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Drugstore balance lower than necessary");
                }
                consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - buyCreate.getValue());
                repository.save(consumer);
                break;
            case FUEL:
                consumer = repository.findByFuelCardNumber(buyCreate.getCardNumber())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card number not found"));
                double tax = (buyCreate.getValue() / 100) * 35;
                buyCreate.setValue(buyCreate.getValue() + tax);

                if (buyCreate.getValue() > consumer.getFuelCardBalance()) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Fuel balance lower than necessary");
                }

                consumer.setFuelCardBalance(consumer.getFuelCardBalance() - buyCreate.getValue());
                repository.save(consumer);
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Establishment type invalid");
        }

        Extract extract = new Extract(buyCreate.getEstablishmentName(), buyCreate.getProductDescription(), new Date(), buyCreate.getCardNumber(), buyCreate.getValue());
        extractService.create(extract);
    }
}
