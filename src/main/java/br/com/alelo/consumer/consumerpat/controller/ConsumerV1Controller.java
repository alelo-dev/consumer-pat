package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerUpdateDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.response.Response;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
public class ConsumerV1Controller implements ConsumerV1Doc, Response {

    private final ConsumerService consumerService;
    private final CardService cardService;

    @GetMapping
    public ResponseEntity<Page<Consumer>> getAllConsumers(
            @PageableDefault(size = 50) final Pageable pageable) {

        return ResponseEntity.ok(consumerService.findConsumersPageable(pageable));
    }

    @PostMapping
    public ResponseEntity<?> createConsumer(
            @RequestBody @Valid ConsumerDTO consumerDTO) {

        Consumer newConsumer = consumerService.createConsumer(consumerDTO);

        if (newConsumer != null)
            return body(201, "Consumer created successfully");

        return body(500, "Failed to create consumer");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateConsumer(
            @PathVariable("id") @NotNull Long id,
            @RequestBody @Valid ConsumerUpdateDTO consumerDTO) {

        Optional<Consumer> consumer = consumerService.getById(id);

        if (consumer.isEmpty())
            return body(404, "Consumer not found");

        Consumer updateConsumer = consumerService.updateConsumer(consumer.get(), consumerDTO);

        if (updateConsumer != null)
            return body(200, "Consumer update successfully");

        return body(500, "Failed to update consumer");
    }

    @PutMapping("/{cardNumber}/balances")
    public ResponseEntity<?> creditCardBalance(
            @PathVariable("cardNumber") @Size(min = 16, max = 16) @NotNull Long cardNumber,
            @RequestParam("value") @NotNull Double value) {

        Card card = consumerService.creditBalance(cardNumber, value);

        if (card != null)
            return body(200, "Balance update successfully");

        return body(500, "Failed to update balance");
    }

    @PostMapping("/{cardNumber}/transactions")
    public ResponseEntity<?> makeTransaction(
            @PathVariable("cardNumber") @Size(min = 16, max = 16) @NotNull Long cardNumber,
            @RequestParam("establishmentType") @NotNull Integer establishmentType,
            @RequestParam("establishmentName") @NotNull String establishmentName,
            @RequestParam("productDescription") @NotNull String productDescription,
            @RequestParam("value") @NotNull Double value) {

        Card card = cardService.getCardByNumber(cardNumber);

        if (card == null)
            return body(404, "Card not found");

        if (establishmentType < 1 || establishmentType > 3)
            return body(404, "Type establishment not found");

        Extract extract = consumerService.buy(establishmentType, establishmentName, cardNumber, productDescription, value);

        if (extract != null)
            return body(200, "Transaction make successfully");

        return body(500, "Failed to make transaction");
    }

}
