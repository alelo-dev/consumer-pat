package br.com.alelo.consumer.consumerpat.api.controller;

import br.com.alelo.consumer.consumerpat.api.mapper.ConsumerMapper;
import br.com.alelo.consumer.consumerpat.domain.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.DepositDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.WrappedPurchaseDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.service.ConsumerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ConsumerController.CONSUMER_ENDPOINT)
public class ConsumerController {

    public static final String CONSUMER_ENDPOINT = "/consumers";
	
    @Autowired
    private ConsumerService consumerService;
    
    @Autowired
    ConsumerMapper mapper;


    @GetMapping
    public List<Consumer> readAll() {
    	return consumerService.findAll();
    }
    
    @GetMapping("/{id}")
    public Consumer readOne(@PathVariable Long id) {
    	return consumerService.findById(id);
    }

    @PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ConsumerDTO consumerDTO) {
    	Consumer consumer = mapper.dtoToEntity(consumerDTO);
    	consumerService.save(consumer);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody ConsumerDTO consumerDTO) {
    	Consumer consumer = mapper.dtoToEntity(id, consumerDTO);
    	consumerService.save(consumer);
    }

    @DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
    	consumerService.delete(id);
	}
    
    @PostMapping("/new-card/{id}")
    public Consumer createCard(@PathVariable Long id, @RequestBody CardDTO card ) {
    	Consumer consumer = consumerService.generateConsumerCard(id,card);
    	return consumer;
    }

     @PostMapping("/deposit/{id}")
     public Consumer deposit(@PathVariable Long id, @RequestBody DepositDTO depositDTO) {
        return consumerService.deposit(id, depositDTO);
     }

     @PostMapping("/buy/{id}")
     public Consumer buy(@PathVariable Long id, @RequestBody WrappedPurchaseDTO wrappedDTO) { 
    	 return consumerService.buy(id, wrappedDTO);
     }
     
}
