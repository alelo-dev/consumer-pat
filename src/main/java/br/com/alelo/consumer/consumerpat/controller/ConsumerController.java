package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.BalanceUpdate;
import br.com.alelo.consumer.consumerpat.model.BuyCreate;
import br.com.alelo.consumer.consumerpat.model.ConsumerCreate;
import br.com.alelo.consumer.consumerpat.model.ConsumerUpdate;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@Tag(name = "Consumer API")
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @ResponseBody
    @Operation(summary = "Get all consumers", description = "Returns all consumers")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Consumer> getAll() {
        return consumerService.getAll();
    }

    @Operation(summary = "Create a consumer", description = "Create a consumer")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void create(@RequestBody @Valid ConsumerCreate consumer) {
        consumerService.create(consumer);
    }

    @Operation(summary = "Update a consumer", description = "Update a consumer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public void updateConsumer(@RequestBody @Valid ConsumerUpdate consumerUpdate) {
        consumerService.update(consumerUpdate);
    }

    @Operation(summary = "Update a consumer balance", description = "Update a consumer balance based by establishment type")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/balance", method = RequestMethod.PATCH)
    public void setBalance(@RequestBody @Valid BalanceUpdate balanceUpdate) {
        consumerService.setBalance(balanceUpdate);
    }

    @Operation(summary = "Buy a product", description = "Buy a product based by establishment type")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public void buy(@RequestBody @Valid BuyCreate buyCreate) {
        consumerService.buy(buyCreate);
    }
}
