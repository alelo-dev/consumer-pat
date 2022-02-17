package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService service;

    /* Deve listar todos os clientes (cerca de 500) */
    @RequestMapping(method = RequestMethod.GET)
    public Page<Consumer> listAllConsumers() {

        return service.listAllConsumers();
    }

    /* Cadastrar novos clientes */
    @RequestMapping(method = RequestMethod.POST)
    public Consumer createConsumer(@RequestBody Consumer consumer) {

        return service.createConsumer(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(method = RequestMethod.PUT)
    public Consumer updateConsumer(@RequestBody Consumer consumer) {
        return service.updateConsumer(consumer);
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/balance", method = RequestMethod.PUT)
    public Card setBalance(int cardNumber, double value) {
        return this.service.setBalance(cardNumber, value);
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public Extract buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        return this.service.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
    }
}
