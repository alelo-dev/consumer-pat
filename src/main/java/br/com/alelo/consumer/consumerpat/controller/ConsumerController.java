package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/consumer")
public class ConsumerController {

    private final ConsumerService service;

    @GetMapping
    public List<Consumer> getAllConsumers() {
        return service.listAllConsumers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createConsumer(@RequestBody Consumer consumer) {
        service.createConsumer(consumer);
    }

    @PutMapping("/{id}")
    public void updateConsumer(@PathVariable("id") Long id, @RequestBody Consumer consumer) {
        // fazer validação com id
        service.updateConsumer(consumer);
    }

    @PutMapping("/{cardNumber}/balance")
    public void setCardBalance(@PathVariable("cardNumber") Integer cardNumber,
                               @RequestParam("value") Double value) {
        service.setBalance(cardNumber, value);
    }

    @PostMapping("/{cardNumber}/transactions")
    public void makeTransaction(@PathVariable("cardNumber") int cardNumber,
                                @RequestParam("establishmentType") int establishmentType,
                                @RequestParam("establishmentName") String establishmentName,
                                @RequestParam("productDescription") String productDescription,
                                @RequestParam("value") double value) {

        service.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
    }

}
