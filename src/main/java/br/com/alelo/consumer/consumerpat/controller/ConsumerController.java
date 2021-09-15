package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.IConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@Controller
@RequestMapping("/")
public class ConsumerController {

    @Autowired
    private IConsumerService consumerService;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "consumer", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return consumerService.listAllConsumers();
    }


    /* Cadastrar novos clientes */
    @RequestMapping(value = "/consumer", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createConsumer(@RequestBody Consumer consumer) {
        consumerService.createConsumer(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/consumer", method = RequestMethod.PUT)
    @ResponseStatus(code = HttpStatus.OK)
    public void updateConsumer(@RequestBody ConsumerDTO consumer) {

        consumerService.updateConsumer(consumer);
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/consumer/balance", method = RequestMethod.PUT)
    @ResponseStatus(code = HttpStatus.OK)
    public void setBalance(String cardNumber, BigDecimal value) {
        consumerService.setBalance(cardNumber, value);
    }

    @ResponseBody
    @RequestMapping(value = "/consumer/buy", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void buy(int establishmentType, String establishmentName, String cardNumber, String productDescription, BigDecimal value) {

        consumerService.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
    }
}
