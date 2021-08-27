package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/consumers")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService service;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return service.listAllConsumers();
    }


    /* Cadastrar novos clientes */
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public void createConsumer(@RequestBody Consumer consumer) {
        service.createConsumer(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
    public void updateConsumer(@RequestBody Consumer consumer) {
        service.updateConsumer(consumer);
    }

    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
    public void setBalance(int cardNumber, double value) {
        service.setBalance(cardNumber, value);
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
    	service.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
    }

}
