package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.dto.MapConsumerDto;
import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.LancamentoRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;


@RestController
@RequestMapping("/consumers")
public class ConsumerController {

    @Autowired
    ConsumerRepository repository;

    @Autowired
    LancamentoRepository extractRepository;

    @Autowired
    ConsumerService consumerService;

    @Autowired
    MapConsumerDto mapConsumerDto;
    
    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @GetMapping
    public List<Consumer> listAllConsumers() {
        return repository.findAll();
    }
    
    @ResponseBody
    @GetMapping("/card/{cardNumer}" )
    public Consumer getByCardNumber(//
    		@PathVariable("cardNumer")
    		Long cardNumer) {
        return repository.findByCardNumber(cardNumer);
    }


    /* Cadastrar novos clientes */    
    @PostMapping
    public ResponseEntity<Void> createConsumer(@RequestBody ConsumerDto consumerDto) {
    	consumerService.save(consumerDto);    	
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Não deve ser possível alterar o saldo do cartão
    @PatchMapping("/{id}")
    public  ResponseEntity<Void> updateConsumer(@PathVariable("id") Long id, 
    		@RequestBody ConsumerDto consumerDto) {
        consumerService.update(id, consumerDto);
        return ResponseEntity.ok().build();
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    
    @PatchMapping("/credit/{cardNumber}")
    public void setBalance(//
    		@PathVariable("cardNumber")
    		Long cardNumber, //
    		
    		@RequestParam("value")
    		double value) {
    	consumerService.credit(cardNumber, value);
    }

    @ResponseBody
    @PatchMapping("/buy/{cardNumber}")
    public void buy(//
    		@PathVariable("cardNumber")
    		Long cardNumber,//

    		@RequestParam("establishmentType")
    		int establishmentType,//
    		
    		@RequestParam("establishmentName")
    		String establishmentName,//
    		
    		@RequestParam("productDescription")
    		String productDescription,//
    		
    		@RequestParam("value")    		
    		double value) {
    	
    	consumerService.buy(cardNumber, value);

    }

}











