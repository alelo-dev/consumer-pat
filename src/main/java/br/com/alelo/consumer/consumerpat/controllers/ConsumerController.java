package br.com.alelo.consumer.consumerpat.controllers;

import br.com.alelo.consumer.consumerpat.dtos.Buy;
import br.com.alelo.consumer.consumerpat.dtos.ConsumerDto;
import br.com.alelo.consumer.consumerpat.entities.Consumer;
import br.com.alelo.consumer.consumerpat.services.ConsumerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/consumer")
@Api("Endpoints to consumer service")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @GetMapping("/consumer-list")
    @ApiOperation(value = "List all consumers", response = Consumer[].class)
    public List<Consumer> listAllConsumers() {
        return consumerService.getConsumersList();
    }

    @PostMapping("/create-consumer")
    @ApiOperation(value = "register new customers")
    public void createConsumer(@RequestBody ConsumerDto consumer) {
        consumerService.saveConsumer(consumer);
    }

    @PutMapping("/update-consumer")
    @ApiOperation(value = "update customer register")
    public void updateConsumer(@RequestBody ConsumerDto consumer) {
        consumerService.updateConsumer(consumer);
    }

    @PutMapping("/card-balance")
    @ApiOperation(value = "set card balance")
    public void setBalance(Integer cardNumber, BigDecimal value) {
        consumerService.setBalance(cardNumber, value);
    }

    @PostMapping("/buy")
    @ApiOperation(value = "finish purchase and save the extract")
    public void buy(@RequestBody Buy buy) {
        consumerService.buy(buy);
    }
}
