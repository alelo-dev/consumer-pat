package br.com.alelo.consumer.consumerpat.application.web.controllers;

import br.com.alelo.consumer.consumerpat.application.web.requests.CardBalanceRequest;
import br.com.alelo.consumer.consumerpat.application.web.requests.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.application.web.requests.DebitCardRequest;
import br.com.alelo.consumer.consumerpat.application.web.wrappers.CardBalanceRequestWrapper;
import br.com.alelo.consumer.consumerpat.application.web.wrappers.ConsumerRequestWrapper;
import br.com.alelo.consumer.consumerpat.application.web.wrappers.DebitCardWrapper;
import br.com.alelo.consumer.consumerpat.domain.dto.CardBalance;
import br.com.alelo.consumer.consumerpat.domain.dto.DebitCard;
import br.com.alelo.consumer.consumerpat.domain.entities.Consumer;
import br.com.alelo.consumer.consumerpat.domain.entities.Extract;
import br.com.alelo.consumer.consumerpat.domain.exceptions.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.exceptions.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.exceptions.ConsumersNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.exceptions.ExtractNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.services.CardService;
import br.com.alelo.consumer.consumerpat.domain.services.ConsumerService;
import br.com.alelo.consumer.consumerpat.domain.services.ExtractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;

    @Autowired
    CardService cardService;

    @Autowired
    ExtractService extractService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/consumers")
    public List<Consumer> findAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) throws ConsumersNotFoundException {
        log.info("obtendo todos clientes");

        return consumerService.findAll(page, size);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/consumers")
    public Consumer save(@RequestBody @NotNull ConsumerRequest consumerRequest) {
        log.info("cadastrando cliente");

        Consumer consumer = new ConsumerRequestWrapper(consumerRequest).toModel();
        return consumerService.save(consumer);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/consumers")
    public Consumer update(@RequestBody @NotNull ConsumerRequest consumerRequest) throws ConsumerNotFoundException {
        log.info("atualizando cliente");

        Consumer consumer = new ConsumerRequestWrapper(consumerRequest).toModel();
        return consumerService.update(consumer);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/consumers/{id}/card-balance")
    public void addBalance(
            @PathVariable int id,
            @RequestBody @NotNull CardBalanceRequest cardBalanceRequest
    ) throws CardNotFoundException {
        log.info("adicionando saldo no cartão");

        CardBalance cardBalance = new CardBalanceRequestWrapper(id, cardBalanceRequest).toModel();
        cardService.addBalance(cardBalance);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/consumers/{id}/buy")
    public void buy(
            @PathVariable int id,
            @RequestBody @NotNull DebitCardRequest debitCardRequest
    ) throws CardNotFoundException {
        log.info("debitando saldo do cartão");

        DebitCard debitCard = new DebitCardWrapper(id, debitCardRequest).toModel();
        cardService.buy(debitCard);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/consumers/{id}/statement")
    public List<Extract> findStatement(
            @PathVariable("id") int consumerId,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) throws ExtractNotFoundException {
        log.info("consultando extrato");

        return extractService.findAll(consumerId, page, size);
    }

}
