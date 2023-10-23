package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/consumer")
public class ConsumerController extends BaseController {
	
	@Autowired
	ConsumerService consumerService;

    /* Listar todos os clientes (obs.: tabela possui cerca de 50.000 registros) */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8" )
    public List<Consumer> listAllConsumers() {
        log.info("obtendo todos clientes");            
        return consumerService.listAllConsumers();
    }

    /* Cadastrar novos clientes */
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public void createConsumer(@RequestBody Consumer consumer) {
    	if(consumer != null) {
    		consumerService.createConsumer(consumer);    		
    	}else {
    		log.error(">>> createConsumer: Não pode criar um Cliente nulo.");
    	}
    }

    // Atualizar cliente, lembrando que não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST,consumes = "application/json;charset=UTF-8")
    public void updateConsumer(@RequestBody Consumer consumer) {
    	if(consumer != null && consumer.id != null) {
    		consumerService.createConsumer(consumer);    		
    	}else {
    		log.error(">>> updateConsumer: Não pode atualizar um Cliente nulo ou sem ID.");
    	}    	
    }

    /*
     * Credito de valor no cartão
     *
     * cardNumber: número do cartão
     * value: valor a ser creditado (adicionado ao saldo)
     */
    
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
    public void setBalance(@RequestParam int cardNumber, @RequestParam double value) {
    	if(cardNumber > 0 && value > 0) {
    		consumerService.setBalance(cardNumber, value);    		
    	}else {
    		log.error(">>> setBalance: Número do cartão ou Valor a ser creditado incorreto.");
    	}  
    	
    }

    /*
     * Débito de valor no cartão (compra)
     *
     * establishmentType: tipo do estabelecimento comercial
     * establishmentName: nome do estabelecimento comercial
     * cardNumber: número do cartão
     * productDescription: descrição do produto
     * value: valor a ser debitado (subtraído)
     */
    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.POST,consumes = "application/json;charset=UTF-8")
    public void buy(@RequestBody BuyDTO buyDto) {
    	if(buyDto != null) {
    		consumerService.buy(buyDto);   		
    	}else {
    		log.error(">>> buy: BuyDTO não pode ser nulo.");
    	}  
    	
    }

}
