package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {
    
  
    ConsumerService consumerService;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return consumerService.list();
    }

    /* Cadastrar novos clientes */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createConsumer(@RequestBody Consumer consumer) {
    	return new ResponseEntity<>(consumerService.create(consumer), HttpStatus.CREATED);       
    }    

    // Nao deve ser possivel alterar o saldo do cartao
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Consumer updateConsumer(@RequestBody Consumer consumer) {
        return consumerService.update(consumer);
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartao.
     * Para isso ele precisa indenficar qual o cartao correto a ser recarregado,
     * para isso deve usar o numero do cartao(cardNumber) fornecido.
     */
    @RequestMapping(value = "/set-card-balance", method = RequestMethod.PUT)
    public void setBalance(int cardNumber, double value) {        
    	consumerService.updateBalance(cardNumber, value);    
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.PUT)
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        consumerService.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
    }

}
