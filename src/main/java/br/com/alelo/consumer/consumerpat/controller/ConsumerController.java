package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.model.*;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Log4j2
@Tag(name = "Consumer API")
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    ConsumerRepository repository;
    ExtractRepository extractRepository;

    public ConsumerController(ConsumerRepository repository, ExtractRepository extractRepository) {
        this.repository = repository;
        this.extractRepository = extractRepository;
    }

    @ResponseBody
    @Operation(summary = "Get all consumers", description = "Returns all consumers")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return repository.getAllConsumers();
    }

    @Operation(summary = "Create a consumer", description = "Create a consumer")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void createConsumer(@RequestBody @Valid ConsumerCreate consumer) {
        repository.save(consumer.toEntity());
    }

    @Operation(summary = "Update a consumer", description = "Update a consumer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public void updateConsumer(@RequestBody @Valid ConsumerUpdate consumer) {
        var consumerFound = repository.findById(consumer.getConsumerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consumer not found"));
        var customerToUpdate = consumer.toEntity();

        customerToUpdate.setConsumerId(consumerFound.getConsumerId());
        customerToUpdate.setFoodCardBalance(consumerFound.getFoodCardBalance());
        customerToUpdate.setFuelCardBalance(consumerFound.getFuelCardBalance());
        customerToUpdate.setDrugstoreCardBalance(consumerFound.getDrugstoreCardBalance());

        repository.save(customerToUpdate);
    }

    @Operation(summary = "Update a consumer balance", description = "Update a consumer balance based by establishment type")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/balance", method = RequestMethod.PATCH)
    public void setBalance(@RequestBody @Valid BalanceUpdate balance) {
        var optionalConsumer = repository.findByDrugstoreNumber(balance.getCardNumber());

        if (optionalConsumer.isPresent()) {
            var consumer = optionalConsumer.get();
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + balance.getValue());
            repository.save(consumer);
            return;
        }

        optionalConsumer = repository.findByFuelCardNumber(balance.getCardNumber());

        if (optionalConsumer.isPresent()) {
            var consumer = optionalConsumer.get();
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() + balance.getValue());
            repository.save(consumer);
            return;
        }

        optionalConsumer = repository.findByFoodCardNumber(balance.getCardNumber());

        if (optionalConsumer.isPresent()) {
            var consumer = optionalConsumer.get();
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() + balance.getValue());
            repository.save(consumer);
            return;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card number not found");
    }

    @Operation(summary = "Buy a product", description = "Buy a product based by establishment type")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public void buy(@RequestBody @Valid BuyCreate buy) {
        switch (EstablishmentType.getById(buy.getEstablishmentType())) {
            case FOOD:
                var consumer = repository.findByFoodCardNumber(buy.getCardNumber())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card number not found"));
                if (buy.getValue() > consumer.getFoodCardBalance()) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Food balance lower than necessary");
                }
                double cashback = (buy.getValue() / 100) * 10;
                buy.setValue(buy.getValue() - cashback);
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() - buy.getValue());
                repository.save(consumer);
                break;
            case DRUGSTORE:
                consumer = repository.findByDrugstoreNumber(buy.getCardNumber())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card number not found"));
                if (buy.getValue() > consumer.getDrugstoreCardBalance()) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Drugstore balance lower than necessary");
                }
                consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - buy.getValue());
                repository.save(consumer);
                break;
            case FUEL:
                consumer = repository.findByFuelCardNumber(buy.getCardNumber())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card number not found"));
                double tax = (buy.getValue() / 100) * 35;
                buy.setValue(buy.getValue() + tax);

                if (buy.getValue() > consumer.getFuelCardBalance()) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Fuel balance lower than necessary");
                }

                consumer.setFuelCardBalance(consumer.getFuelCardBalance() - buy.getValue());
                repository.save(consumer);
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Establishment type invalid");
        }

        Extract extract = new Extract(buy.getEstablishmentName(), buy.getProductDescription(), new Date(), buy.getCardNumber(), buy.getValue());
        extractRepository.save(extract);
    }
}
