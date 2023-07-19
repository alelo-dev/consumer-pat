package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.BuyModel;
import br.com.alelo.consumer.consumerpat.model.CardBalanceModel;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("getConsumer")
    public ResponseEntity<Page<Consumer>> getAllConsumers(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "100") Integer itemsPerPage) {

        Page<Consumer> response = consumerService.getAllConsumers(page, itemsPerPage);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("consumer")
    public ResponseEntity<Consumer> createConsumer(@RequestBody Consumer consumer) {
        return new ResponseEntity<>(consumerService.save(consumer), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PatchMapping("consumer")
    public ResponseEntity<Consumer> updateConsumer(@RequestBody Consumer consumer) {
        return new ResponseEntity<>(consumerService.update(consumer), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PatchMapping("balance")
    public ResponseEntity<Consumer> addBalance(@RequestBody @Valid CardBalanceModel cardBalanceModel) {

        Consumer response = ConsumerService.addValue(cardBalanceModel.getCardNumber(), cardBalanceModel.getValue());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping(value = "buy")
    public ResponseEntity<Consumer> buy(@RequestBody @Valid BuyModel buyModel) {
        log.info("Buying product for card: " + buyModel.getCardNumber());

        return new ResponseEntity<>(consumerService.buy(buyModel), HttpStatus.OK);
    }

}
