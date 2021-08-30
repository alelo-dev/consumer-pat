package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.config.SwaggerConfig.ApiPageable;
import br.com.alelo.consumer.consumerpat.domain.payload.ConsumerPayload;
import br.com.alelo.consumer.consumerpat.domain.response.ApiErrorResponse;
import br.com.alelo.consumer.consumerpat.domain.response.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequestMapping("/consumers")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService service;

    /* Deve listar todos os clientes (cerca de 500) */
    @GetMapping
    @ApiPageable
    @ApiOperation(value = "List all clients")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Success", response = ConsumerResponse.class)
	})	 
    public Page<ConsumerResponse> listAllConsumers(@ApiIgnore Pageable page) {
        return service.listAllConsumers(page);
    }


    /* Cadastrar novos clientes */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiOperation(value = "Create new Consumer")
   	@ApiResponses(value = {
   		@ApiResponse(code = 201, message = "Success", response = Void.class),
   		@ApiResponse(code = 409, message = "Card with given number already exists", response = ApiErrorResponse.class)
   	})
    public void createConsumer(@RequestBody ConsumerPayload consumer) {
        service.createConsumer(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @ApiOperation(value = "Create new Consumer")
   	@ApiResponses(value = {
   		@ApiResponse(code = 202, message = "Success", response = Void.class),
   		@ApiResponse(code = 404, message = "Consumer with given id not found", response = ApiErrorResponse.class),
   		@ApiResponse(code = 409, message = "Card with given number already exists binded to another consumer", response = ApiErrorResponse.class)
   	})
    public void updateConsumer(@PathVariable("id") @ApiParam(example = "1") Integer id, @RequestBody ConsumerPayload consumer) {
        service.updateConsumer(id, consumer);
    }

}
