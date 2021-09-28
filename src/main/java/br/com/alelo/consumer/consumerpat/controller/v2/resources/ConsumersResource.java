package br.com.alelo.consumer.consumerpat.controller.v2.resources;

import br.com.alelo.consumer.consumerpat.domain.dto.v2.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.domain.exception.ConsumerNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Consumer operations")
@RequestMapping("/v2/consumers")
public interface ConsumersResource {

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ConsumerDTO.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "List all consumers")
    @GetMapping
    ResponseEntity<List<ConsumerDTO>> listAllConsumers();

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ConsumerDTO.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Add a new Consumer")
    @PostMapping
    ResponseEntity<ConsumerDTO> createConsumer(@RequestBody ConsumerDTO consumer);

    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Consumer Updated", response = ConsumerDTO.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Update a consumer")
    @PatchMapping("/{id-consumer}")
    ResponseEntity<ConsumerDTO>  updateConsumer(@PathVariable("id-consumer") int idConsumer, @RequestBody ConsumerDTO consumer) throws ConsumerNotFoundException;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK",  response = ConsumerDTO.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Get consumer by ID")
    @GetMapping("/{id-consumer}")
    ResponseEntity<ConsumerDTO> getConsumerById(@PathVariable("id-consumer") int idConsumer) throws ConsumerNotFoundException;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK",  response = ConsumerDTO.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Get consumer by CardNumber")
    @GetMapping("/card/{card-number}")
    ResponseEntity<ConsumerDTO>  getConsumerByCardNumber(@PathVariable("card-number") int cardNumber);
}