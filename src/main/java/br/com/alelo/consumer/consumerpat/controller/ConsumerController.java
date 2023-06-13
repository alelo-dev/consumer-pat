package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Log4j2
@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ExtractRepository extractRepository;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/consumerList")
    public List<Consumer> listAllConsumers() {
        log.info("Obtendo todos os clientes");
        return repository.findAll();
    }

    @PostMapping("/createConsumer")
    public void createConsumer(@RequestBody Consumer consumer) {
        repository.save(consumer);
    }

    @PostMapping("/updateConsumer")
    public void updateConsumer(@RequestBody Consumer consumer) {
        repository.save(consumer);
    }
    /* Tipos dos estabelcimentos:
     *    1) Alimentação (Food)
     *    2) Farmácia (DrugStore)
     *    3) Posto de combustivel (Fuel)
     */

    @GetMapping("/setcardbalance")
    public void setBalance(@RequestParam int cardNumber, @RequestParam double value) {
        Optional<Consumer> consumer = repository.findByDrugstoreNumber(cardNumber);

        if (consumer.isPresent()) {
            Consumer pharmacyConsumer = consumer.get();
            pharmacyConsumer.setDrugstoreCardBalance(pharmacyConsumer.getDrugstoreCardBalance() + value);
            repository.save(pharmacyConsumer);
        } else {
            consumer = repository.findByFoodCardNumber(cardNumber);
            if (consumer.isPresent()) {
                Consumer foodConsumer = consumer.get();
                foodConsumer.setFoodCardBalance(foodConsumer.getFoodCardBalance() + value);
                repository.save(foodConsumer);
            } else {
                consumer = repository.findByFuelCardNumber(cardNumber);
                if (consumer.isPresent()) {
                    Consumer fuelConsumer = consumer.get();
                    fuelConsumer.setFuelCardBalance(fuelConsumer.getFuelCardBalance() + value);
                    repository.save(fuelConsumer);
                }
            }
        }
    }

    @GetMapping("/buy")
    @ResponseBody
    public void buy(
            @RequestParam int establishmentType,
            @RequestParam String establishmentName,
            @RequestParam int cardNumber,
            @RequestParam String productDescription,
            @RequestParam double value) {

        Optional<Consumer> consumerOptional = Optional.empty();
        final double finalValue;

        if (establishmentType == 1) {
            Double cashback = (value / 100) * 10;
            finalValue = value - cashback;

            consumerOptional = repository.findByFoodCardNumber(cardNumber);
        } else if (establishmentType == 2) {
            consumerOptional = repository.findByDrugstoreNumber(cardNumber);
            finalValue = value;
        } else {
            Double tax = (value / 100) * 35;
            finalValue = value + tax;

            consumerOptional = repository.findByFuelCardNumber(cardNumber);
        }

        consumerOptional.ifPresent(consumer -> {
            if (establishmentType == 1) {
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() - finalValue);
            } else if (establishmentType == 2) {
                consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - finalValue);
            } else {
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() - finalValue);
            }
            repository.save(consumer);

            Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, finalValue);
            extractRepository.save(extract);
        });
    }
}