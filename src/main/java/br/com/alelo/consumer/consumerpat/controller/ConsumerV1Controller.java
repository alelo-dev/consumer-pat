package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerUpdateDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

@AllArgsConstructor

@Validated
@RestController
@RequestMapping("/v1/consumers")
public class ConsumerV1Controller {

    private final ConsumerService service;

    @GetMapping
    public ResponseEntity<Page<Consumer>> getAllConsumers(
            @PageableDefault(size = 50) final Pageable pageable) {

        return ResponseEntity.ok(service.findConsumersPageable(pageable));
    }

    @PostMapping
    public ResponseEntity<String> createConsumer(
            @RequestBody @Valid ConsumerDTO consumerDTO) {

        Consumer newConsumer = service.createConsumer(consumerDTO);

        if (newConsumer != null)
            return ResponseEntity.status(HttpStatus.CREATED).body("Consumer created successfully");

        return ResponseEntity.internalServerError().body("Failed to create consumer");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateConsumer(
            @PathVariable("id") @NotNull Long id,
            @RequestBody @Valid ConsumerUpdateDTO consumerDTO) {

        Optional<Consumer> consumer = service.getById(id);

        if (consumer.isEmpty())
            return ResponseEntity.notFound().build();

        Consumer updateConsumer = service.updateConsumer(consumer.get(), consumerDTO);

        if (updateConsumer != null)
            return ResponseEntity.ok("Consumer update successfully");

        return ResponseEntity.internalServerError().body("Failed to update consumer");
    }

    @PutMapping("/{cardNumber}/balances")
    public ResponseEntity<String> creditCardBalance(
            @PathVariable("cardNumber") @Size(min = 16, max = 16) @NotNull Long cardNumber,
            @RequestParam("value") @NotNull Double value) {

        Card card = service.creditBalance(cardNumber, value);

        if (card != null)
            return ResponseEntity.ok("Balance update successfully");

        return ResponseEntity.internalServerError().body("Failed to update balance");
    }

    @PostMapping("/{cardNumber}/transactions")
    public void makeTransaction(
            @PathVariable("cardNumber") @Size(min = 16, max = 16) @NotNull Long cardNumber,
            @RequestParam("establishmentType") @NotNull Integer establishmentType,
            @RequestParam("establishmentName") @NotNull String establishmentName,
            @RequestParam("productDescription") @NotNull String productDescription,
            @RequestParam("value") @NotNull Double value) {

        service.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
    }

}
