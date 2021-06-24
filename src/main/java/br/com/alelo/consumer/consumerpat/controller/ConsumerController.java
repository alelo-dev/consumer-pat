package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public Page<Consumer> listAllConsumers(@PageableDefault(size = 500) Pageable pageable) {
        return consumerService.getAllConsumers(pageable);
    }

    /* Cadastrar novos clientes */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public Consumer createConsumer(@RequestBody Consumer consumer) {
    	return consumerService.createConsumer(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT)
    public Consumer updateConsumer(@RequestBody Consumer consumer) {
    	consumer = consumerService.updateConsumer(consumer);
    	if (consumer == null) {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Consumer Not Found");
    	} else {
    		return consumer;
    	}
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/cardbalance", method = RequestMethod.PUT)
    public void setBalance(int cardNumber, double value) {
    	if (!consumerService.setBalance(cardNumber, value)) {
    		throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
    	}
    }

    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public Extract buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
    	Extract extract = consumerService.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
    	if (extract == null) {
    		throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
    	} else {
    		return extract;
    	}
    }

}
