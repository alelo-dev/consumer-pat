package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.model.BuyModel;
import br.com.alelo.consumer.consumerpat.model.CardBalanceModel;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/")
public class ConsumerController {

    ConsumerService consumerService;

    ExtractRepository extractRepository;

    public ConsumerController(ConsumerService consumerService, ExtractRepository extractRepository) {
        this.consumerService = consumerService;
        this.extractRepository = extractRepository;
    }
    @GetMapping
    public ResponseEntity<Page<Consumer>> getAllConsumers(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "100") Integer itemsPerPage) {
        log.info("Getting all clients");

        Page<Consumer> response = consumerService.getAllConsumers(page, itemsPerPage);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /* Cadastrar novos clientes */
    @PostMapping
    public ResponseEntity<Consumer> createConsumer(@RequestBody Consumer consumer) {
        log.info("Creating consumer");
        return new ResponseEntity<>(consumerService.save(consumer), HttpStatus.CREATED);
    }

    // Atualizar cliente, lembrando que não deve ser possível alterar o saldo do cartão
    @PatchMapping
    public ResponseEntity<Consumer> updateConsumer(@RequestBody Consumer consumer) {
        log.info("Updating consumer");
        return new ResponseEntity<>(consumerService.update(consumer), HttpStatus.OK);
    }

    @PatchMapping(value = "card/balance")
    public ResponseEntity<Consumer> addBalance(@RequestBody @Valid CardBalanceModel cardBalanceModel) {
        log.info("Adding balance to card: "+ cardBalanceModel.getCardNumber());

        Consumer response = ConsumerService.addValue(cardBalanceModel.getCardNumber(), cardBalanceModel.getValue());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/buy")
    public ResponseEntity<Consumer> buy(@RequestBody @Valid BuyModel buyModel) {
        log.info("Buying product for card: " + buyModel.getCardNumber());

        return new ResponseEntity<>(consumerService.buy(buyModel), HttpStatus.OK);
    }

}
