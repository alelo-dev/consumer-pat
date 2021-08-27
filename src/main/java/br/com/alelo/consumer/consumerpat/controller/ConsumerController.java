package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/consumers")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService service;

    /* Deve listar todos os clientes (cerca de 500) */
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<ConsumerDTO> listAllConsumers() {
        return service.listAllConsumers();
    }


    /* Cadastrar novos clientes */
    @PostMapping
    public void createConsumer(@RequestBody Consumer consumer) {
        service.createConsumer(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping("/{id}")
    public void updateConsumer(@PathVariable("id") Integer id, @RequestBody Consumer consumer) {
        service.updateConsumer(consumer);
    }

    @PostMapping("/cards/{cardNumber}")
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
