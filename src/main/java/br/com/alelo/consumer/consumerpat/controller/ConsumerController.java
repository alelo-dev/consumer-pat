package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/consumer")
public class ConsumerController {

    private final ConsumerService service;

    /* Listar todos os clientes (obs.: tabela possui cerca de 50.000 registros) */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return service.listAllConsumers();
    }

    /* Cadastrar novos clientes */
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public void createConsumer(@RequestBody Consumer consumer) {
        service.createConsumer(consumer);
    }

    // Atualizar cliente, lembrando que não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
    public void updateConsumer(@RequestBody Consumer consumer) {
        service.updateConsumer(consumer);
    }

    /*
     * Credito de valor no cartão
     *
     * cardNumber: número do cartão
     * value: valor a ser creditado (adicionado ao saldo)
     */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
    public void setBalance(int cardNumber, double value) {
        service.setBalance(cardNumber, value);
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
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        service.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
    }

}
