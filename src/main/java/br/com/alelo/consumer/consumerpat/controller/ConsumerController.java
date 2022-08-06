package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.config.ApiPageable;
import br.com.alelo.consumer.consumerpat.request.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.response.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequestMapping(value = "/consumer", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @ApiOperation(value = "Get all existing consumers", notes = "Get all existing data consumers", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Consumers data found", response = ConsumerResponse.class),
            @ApiResponse(code = 422, message = "Document already exists in base")
    })
    @GetMapping
    @ApiPageable
    public ResponseEntity<Page<ConsumerResponse>> listAllConsumers(@ApiIgnore Pageable pageable) {
        return ResponseEntity.ok(consumerService.listAllConsumers(pageable));
    }


    @ApiOperation(value = "Creates a new consumer", notes = "Creates a new consumer related to customers Alelo", httpMethod = "POST")
    @ApiResponses({@ApiResponse(code = 201, message = "New consumer created successfully", response = ConsumerResponse.class)})
    @PostMapping
    public ResponseEntity<ConsumerResponse> createConsumer(@Validated @RequestBody ConsumerRequest consumerRequest) {
        return new ResponseEntity<>(consumerService.createConsumer(consumerRequest), HttpStatus.CREATED);
    }


    @ApiOperation(value = "Updates a consumer", notes = "Updates a consumer related to customers Alelo", httpMethod = "PUT")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Consumer updated successfully", response = ConsumerResponse.class),
            @ApiResponse(code = 404, message = "Consumer not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ConsumerResponse> updateConsumer(@PathVariable("id") Long id, @Validated @RequestBody ConsumerRequest consumerRequest) {
      return new ResponseEntity<>(consumerService.updateConsumer(id, consumerRequest), HttpStatus.OK);
    }

    @ApiOperation(value = "Find cards for consumer", notes = "Find cards for consumer related to customers Alelo", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Consumer for cards found", response = ConsumerResponse.class),
            @ApiResponse(code = 404, message = "Consumer not found")
    })
    @GetMapping("/{documentNumber}")
    public ResponseEntity<ConsumerResponse> findCardsForConsumer(@PathVariable("documentNumber") String documentNumber) {
        return new ResponseEntity<>(consumerService.findCardsForConsumer(documentNumber), HttpStatus.OK);
    }




}
