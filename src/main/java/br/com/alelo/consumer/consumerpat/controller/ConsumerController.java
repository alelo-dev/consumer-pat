package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.ResponseEntity.*;

@AllArgsConstructor
@RestController
@RequestMapping("consumers")
public class ConsumerController {

    private ConsumerService consumerService;

    @GetMapping
    public ResponseEntity<Page<Consumer>> getConsumers(Pageable pageable) {
        Page<Consumer> consumers = consumerService.findAll(pageable);
        return consumers.isEmpty() ? noContent().build() : ok(consumers);
    }

    @PostMapping
    public ResponseEntity<Consumer> postConsumer(@RequestBody Consumer newConsumer) {
        consumerService.save(newConsumer);
        return created(null).body(newConsumer);
    }

    @PatchMapping("/{consumerId}")
    public ResponseEntity<Consumer> patchConsumer(
            @PathVariable int consumerId, @RequestBody Consumer updatedConsumer) {
        updatedConsumer.setId(consumerId);
        consumerService.save(updatedConsumer);
        return ok(updatedConsumer);
    }

    @PatchMapping("/{consumerId}/card-balance/{cardNumber}/{valueToAdd}")
    public ResponseEntity<Void> patchCardBalance(
            @PathVariable int consumerId, @PathVariable String cardNumber, @PathVariable BigDecimal valueToAdd) {
        consumerService.addValueToCard(consumerId, valueToAdd, cardNumber);
        return ok().build();
    }

}
