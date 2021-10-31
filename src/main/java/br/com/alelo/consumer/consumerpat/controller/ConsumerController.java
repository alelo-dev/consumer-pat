package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import io.swagger.annotations.ApiOperation;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
    private ConsumerService service;

    @ApiOperation(value="Returns all customers")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers(			
    		@RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
        return service.listAllConsumers(pageable);
    }

    @SuppressWarnings("rawtypes")
	@ApiOperation(value="Creates a new customer")
    @RequestMapping(value = "/createConsumer", method = RequestMethod.PUT)
    public ResponseEntity createConsumer(@RequestBody Consumer consumer) {
        service.createConsumer(consumer);
        return ResponseEntity.ok().build();
    }
    
    @SuppressWarnings("rawtypes")
	@ApiOperation(value="Updates customer")
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
    public ResponseEntity updateConsumer(@RequestBody Consumer consumer) {
        service.updateConsumer(consumer);
        return ResponseEntity.ok().build();
    }

    @SuppressWarnings("rawtypes")
	@ApiOperation(value="Sets card balance")
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.POST)
    public ResponseEntity setBalance(@RequestParam(required = true) int cardNumber, @RequestParam(required = true) double value) {
        try {
			service.setBalance(cardNumber, value);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity
		            .status(HttpStatus.BAD_REQUEST)
		            .body(e.getMessage());
		}
    }

    @SuppressWarnings("rawtypes")
	@ApiOperation(value="Registers a buying")
    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public ResponseEntity buy(@RequestParam(required = true) int establishmentType, 
    		@RequestParam(required = true) String establishmentName, 
    		@RequestParam(required = true) int cardNumber, 
    		@RequestParam(required = true) String productDescription, 
    		@RequestParam(required = true) double value) {
        try {
			service.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
		            .body(e.getMessage());
		}
    }

}
