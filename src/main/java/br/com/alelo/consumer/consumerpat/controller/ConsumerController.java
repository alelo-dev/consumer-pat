package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerService service;


    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return service.listAllConsumer();
    }


    /* Cadastrar novos clientes */
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public ResponseEntity<String> createConsumer(@RequestBody Consumer consumer) {
    	try {
    		service.createConsumer(consumer);
    		return new ResponseEntity<>("Cliente cadastrado com sucesso",HttpStatus.OK);
    	} catch(Exception e) {
    		return new ResponseEntity<>("Erro ao criar nova entidade",HttpStatus.BAD_REQUEST);
    	}
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.PUT)
    public ResponseEntity<String> updateConsumer(@RequestBody Consumer consumer) {
        
    	try{
    		
    		return new ResponseEntity<>(service.updateConsumer(consumer),HttpStatus.OK);
    	}catch(Exception e) {
    		return new ResponseEntity<>("Erro ao atualizar nova entidade",HttpStatus.BAD_REQUEST);
    	}
		
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/setbalance", method = RequestMethod.POST)
    public ResponseEntity<String> setBalance(Consumer consumer, double value) {
    	try{
    		
    		return new ResponseEntity<>(service.setBalance(consumer,value),HttpStatus.OK);
    	}catch(Exception e) {
    		return new ResponseEntity<>("Erro ao atualizar o saldo",HttpStatus.BAD_REQUEST);
    	}
		
    }


    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public ResponseEntity<String> buy(Extract extract, Consumer consumer, double value, int establishmentType) {
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
        *
        * Tipos de estabelcimentos
        * 1 - Alimentação (food)
        * 2 - Farmácia (DrugStore)
        * 3 - Posto de combustivel (Fuel)
        */
    	try{
    		return new ResponseEntity<>(service.buy(extract,consumer, value,establishmentType),HttpStatus.OK);
    	}catch(Exception e) {
    		return new ResponseEntity<>("Erro ao realizar compra",HttpStatus.BAD_REQUEST);
    	}
    }
}
