package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.CreditCardDto;
import br.com.alelo.consumer.consumerpat.exception.BadCardException;
import br.com.alelo.consumer.consumerpat.exception.BadTypeEstablishmentException;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;


@Controller
@RequestMapping("/consumer/api/v1")
public class ConsumerController {

    @Autowired
    ConsumerService service;

    @Autowired
    ExtractRepository extractRepository;


    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public List<ConsumerDTO> listAllConsumers() {
        return service.getAllConsumersList();
    }


    /* Cadastrar novos clientes */
    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createConsumer(@RequestBody ConsumerDTO dto) {
        try {
        	service.createConsumer(dto);
        	return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(BusinessException e) {
        	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateConsumer(@PathVariable Integer id, @RequestBody ConsumerDTO consumer) {
    	try {
        	service.updateConsumer(consumer,id);
        	return new ResponseEntity<>(HttpStatus.CREATED);
    	}
		 catch (ConsumerNotFoundException e) {
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

    	catch(BusinessException e) {
        	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}	
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> setBalance(@RequestBody CreditCardDto dto) {
        try {
        	service.setBalance(dto);
        	return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (BadCardException e) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
        catch(BusinessException e) {
        	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buy(@RequestBody BuyDTO dto) {
    	try {
        	service.buy(dto);
        	return new ResponseEntity<>(HttpStatus.CREATED);
        }
    	catch (BadTypeEstablishmentException e) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
        catch(BusinessException e) {
        	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }

}
