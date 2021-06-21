package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.parameter.BuyParameter;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.ResponseEntity.*;


@AllArgsConstructor
@Controller
@RequestMapping("/consumers")
public class ConsumerController {

    private ConsumerService service;

    @GetMapping
    public ResponseEntity<Page<Consumer>> getConsumers(Pageable pageable) {
        Page<Consumer> consumers = service.findAllConsumers(pageable);
        return consumers.isEmpty() ? noContent().build() : ok(consumers);
    }

    @PostMapping
    public ResponseEntity<Consumer> postConsumer(@RequestBody Consumer newConsumer) {
        service.save(newConsumer);
        return created(null).body(newConsumer);
    }

    @PatchMapping("/{consumerId}")
    public ResponseEntity<Consumer> patchConsumer(
            @PathVariable int consumerId, @RequestBody Consumer updatedConsumer) {
        updatedConsumer.setId(consumerId);
        service.save(updatedConsumer);
        return ok(updatedConsumer);
    }

    @PatchMapping("/{consumerId}/card-balance/{cardNumber}/{valueToAdd}")
    public ResponseEntity<Void> patchCardBalance(
            @PathVariable int consumerId, @PathVariable String cardNumber, @PathVariable BigDecimal valueToAdd) {

        service.addValueToCard(consumerId, valueToAdd, cardNumber);

        return ok().build();
    }

    @PostMapping("/buy")
    public ResponseEntity<Void> buyProduct(@RequestBody BuyParameter parameter) {

        service.buy(parameter);

        return created(null).build();
    }

}
