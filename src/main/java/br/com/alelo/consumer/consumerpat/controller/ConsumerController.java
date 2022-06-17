package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;


@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/consumerList")
    public List<Consumer> listAllConsumers() {
        return consumerService.getAllConsumers();
    }

    /* Cadastrar novos clientes */
    @PostMapping("/createConsumer")
    public void createConsumer(@RequestBody Consumer consumer) {
    	consumerService.createConsumer(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping("/updateConsumer")
    public void updateConsumer(@RequestBody Consumer consumer) {
    	consumerService.updateConsumer(consumer);
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @PatchMapping("/{cardNumber}/setCardBalance")
    public void setBalance(@PathVariable int cardNumber, @RequestParam double balance) {
        consumerService.updateBalanceByCardNumber(cardNumber, balance);
    }

    @PatchMapping("/buy")
    public void buy(@RequestParam int establishmentType, @RequestParam String establishmentName, 
    		@RequestParam int cardNumber, @RequestParam String productDescription, @RequestParam double value) {
        consumerService.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
    }

}
