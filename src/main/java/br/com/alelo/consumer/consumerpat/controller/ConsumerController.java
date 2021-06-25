package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.dto.RequestBuy;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import io.swagger.annotations.ApiOperation;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerService service;

    @Autowired
    ExtractRepository extractRepository;


    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @ApiOperation(value="Lista clientes")
    @RequestMapping(value = "/consumer/{initialPage}/{limitPage}", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers(@PathVariable  int initialPage, @PathVariable int limitPage) {
        return service.listAllConsumers(initialPage, limitPage);
    }


    /* Cadastrar novos clientes */
    @ResponseBody
    @RequestMapping(value = "/consumer", method = RequestMethod.POST)
    @ApiOperation(value = "Cadastra novos clientes")
    public void consumer(@RequestBody Consumer consumer) {
        service.consumer(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/consumer", method = RequestMethod.PUT)
    @ApiOperation(value = "Altera o saldo do cartão")
    public void updateConsumer(@RequestBody Consumer consumer) {
        service.updateConsumer(consumer);
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/setcardbalance/{cardNumber}", method = RequestMethod.PUT)
    @ApiOperation(value ="Credita saldo no cartão informado")
    public void setBalance(@PathVariable int cardNumber, double value) {
       service.setBalance(cardNumber, value);
    }

    @ResponseBody
    @RequestMapping(value = "/buy/{cardNumber}", method = RequestMethod.PUT)
    @ApiOperation(value = "Registra compra no cartão")
    public void buy(@PathVariable int cardNumber,  @RequestBody RequestBuy request) {
    	service.buy(cardNumber, request);
    }
 }
