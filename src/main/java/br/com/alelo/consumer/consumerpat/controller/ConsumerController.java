package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public List<ConsumerDto> listAllConsumers() {
    	return this.consumerService.findAll();
    }


    /* Cadastrar novos clientes */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createConsumer(@RequestBody ConsumerDto consumer) {
        this.consumerService.save(consumer);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateConsumer(@RequestBody ConsumerDto consumer) {
    	this.consumerService.save(consumer);
    	return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteConsumer(@PathVariable("id") int id) {
    	this.consumerService.delete(id);
    	return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
