package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
    ConsumerService cusomerService;


	/* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return this.cusomerService.listAllConsumers();
    }


    /* Cadastrar novos clientes */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Consumer> createConsumer(@RequestBody Consumer consumer) {
    	return ResponseEntity.ok(this.cusomerService.createOrUpdateConsumer(consumer));
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<Consumer> updateConsumer(@RequestBody Consumer consumer) {
        return ResponseEntity.ok(this.cusomerService.updateConsumer(consumer));
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
    public void setBalance(int cardNumber, double value) {
        this.cusomerService.setBalance(cardNumber, value);
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        this.cusomerService.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
    }

}
