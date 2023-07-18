package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/consumer")
public class ConsumerController {
    ConsumerRepository repository;
    ExtractRepository extractRepository;

    public ConsumerController(ConsumerRepository repository, ExtractRepository extractRepository) {
        this.repository = repository;
        this.extractRepository = extractRepository;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return repository.getAllConsumers();
    }

    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public void createConsumer(@RequestBody Consumer consumer) {
        repository.save(consumer);
    }

    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
    public void updateConsumer(@RequestBody Consumer consumer) {
        var consumerFound = repository.findById(consumer.getConsumerId()).orElseThrow(IllegalArgumentException::new);

        consumer.setFoodCardBalance(consumerFound.getFoodCardBalance());
        consumer.setFuelCardBalance(consumerFound.getFuelCardBalance());
        consumer.setDrugstoreCardBalance(consumerFound.getDrugstoreCardBalance());

        repository.save(consumer);
    }

    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
    public void setBalance(int cardNumber, double value) {
        var optionalConsumer = repository.findByDrugstoreNumber(cardNumber);

        if (optionalConsumer.isPresent()) {
            var consumer = optionalConsumer.get();
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
            repository.save(consumer);
            return;
        }

        optionalConsumer = repository.findByFuelCardNumber(cardNumber);

        if (optionalConsumer.isPresent()) {
            var consumer = optionalConsumer.get();
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
            repository.save(consumer);
            return;
        }

        optionalConsumer = repository.findByFoodCardNumber(cardNumber);

        if (optionalConsumer.isPresent()) {
            var consumer = optionalConsumer.get();
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
            repository.save(consumer);
            return;
        }

        throw new IllegalArgumentException("Card number not found");
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        switch (establishmentType) {
            case 1:
                double cashback = (value / 100) * 10;
                value = value - cashback;
                var consumer = repository.findByFoodCardNumber(cardNumber).orElseThrow(IllegalArgumentException::new);
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
                repository.save(consumer);
                break;
            case 2:
                consumer = repository.findByDrugstoreNumber(cardNumber).orElseThrow(IllegalArgumentException::new);
                consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
                repository.save(consumer);
                break;
            case 3:
                double tax = (value / 100) * 35;
                value = value + tax;
                consumer = repository.findByFuelCardNumber(cardNumber).orElseThrow(IllegalArgumentException::new);
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
                repository.save(consumer);
                break;
            default:
                throw new IllegalArgumentException("Establishment type invalid");
        }

        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
        extractRepository.save(extract);
    }
}
