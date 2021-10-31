package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
    private ConsumerService service;

    @ApiOperation(value="Returns all customers")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/consumerList")
    public List<Consumer> listAllConsumers(			
    		@RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
        return service.listAllConsumers(pageable);
    }

    @SuppressWarnings("rawtypes")
	@ApiOperation(value="Creates a new customer")
    @PutMapping(value = "/createConsumer")
    public ResponseEntity createConsumer(@RequestBody Consumer consumer) {
        service.createConsumer(consumer);
        return ResponseEntity.ok().build();
    }
    
    @SuppressWarnings("rawtypes")
	@ApiOperation(value="Updates customer")
    @PostMapping(value = "/updateConsumer")
    public ResponseEntity updateConsumer(@RequestBody Consumer consumer) {
        service.updateConsumer(consumer);
        return ResponseEntity.ok().build();
    }

    @SuppressWarnings("rawtypes")
	@ApiOperation(value="Sets card balance")
    @PostMapping(value = "/setcardbalance")
    public ResponseEntity setBalance(@RequestParam(required = true) int cardNumber, @RequestParam(required = true) double value) {
        try {
			service.setBalance(cardNumber, value);
			return ResponseEntity.ok().build();
		} catch (BusinessException e) {
			return ResponseEntity
		            .status(HttpStatus.BAD_REQUEST)
		            .body(e.getMessage());
		}
    }

    @SuppressWarnings("rawtypes")
	@ApiOperation(value="Registers a buying")
    @ResponseBody
    @PostMapping(value = "/buy")
    public ResponseEntity buy(@RequestParam(required = true) int establishmentType, 
    		@RequestParam(required = true) String establishmentName, 
    		@RequestParam(required = true) int cardNumber, 
    		@RequestParam(required = true) String productDescription, 
    		@RequestParam(required = true) double value) {
        try {
			service.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
			return ResponseEntity.ok().build();
		} catch (BusinessException e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
		            .body(e.getMessage());
		}
    }

}
