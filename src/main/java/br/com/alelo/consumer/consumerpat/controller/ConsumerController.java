package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/consumer")
public class ConsumerController {

    private final ConsumerService consumerService;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/consumer-list")
    public List<Consumer> listAllConsumers() {
        return consumerService.getAllConsumersList();
    }


    /* Cadastrar novos clientes */
    @PostMapping("/create-consumer")
    @ResponseStatus(HttpStatus.CREATED)
    public void createConsumer(@RequestBody Consumer consumer) {
        consumerService.createConsumer(consumer);
    }


    @PutMapping(value = "/update-consumer")
    public void updateConsumer(@RequestBody Consumer consumer) {
        consumerService.updateConsumer(consumer);
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @PutMapping(value = "/set-card-balance")
    public void setBalance(int cardNumber, double value) {
        consumerService.setBalance(cardNumber, value);
    }

    @PostMapping(value = "/buy")
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        consumerService.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
    }

}
